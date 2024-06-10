package oopb3;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import oopb3.agents.Ameise;
import oopb3.agents.Vogel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Quellen:
 * https://docs.oracle.com/javase/8/javafx/graphics-tutorial/javafx-3d-graphics.htm
 * https://www.tutorialspoint.com/javafx/javafx_3d_shapes.htm
 *
 * History Constraint: JavaFX wird auf der ausfuehrenden Maschine unterstuetzt und Applikation3d wurde ueber JavaFX mittels launch initialisiert
 *
 * NOTE: JavaFX Applikation die in Test.main mittels der JavaFX Funktion launch aufgerufen wird.
 * Diese Klasse kuemmert sich um den Aufbau des Szenengraphen inklusive Kamera (+ deren Steuerung) und die Ablaufsteuerung der Simulation
 * und die Aktualisierung der Render-Objekte anhand der Simulation.
 */
public class Applikation3d extends Application {

    /**
     * Group Elemente des Szenengraphen die dargestellt werden.
     */
    private Group root = new Group();
    private Group waende = new Group();
    private Group agents = new Group();
    private Group world = new Group();
    private PerspectiveCamera kamera = new PerspectiveCamera(true);
    private Group3D kamera3d = new Group3D();
    private List<Box> vogelListe;
    private List<Sphere> ameisenListe;

    private Schwarm3d schwarm;
    /**
     * Kamera-Konstanten
     */
    // Entfernung der Kamera zum Punkt 0,0,0 entlang der Z-Achse
    private static final double KAMERA_DISTANZ = -2500;
    // Initialer Winkel der Kamera entlang der X Achse
    private static final double WINKEL_X = -20;
    // Initialer Winkel der Kamera entlang der Y Achse
    private static final double WINKEL_Y = -30;
    // Gibt an wie nah Objekte der Kamera kommen, bis sie nicht mehr dargestellt werden
    private static final double KAMERA_NAH = 0.1;
    // Gibt an wie weit die Kamera Objekte zeigt
    private static final double KAMERA_FERN = 10000.0;

    private static final BoundingBox visualisierungsHimmel = new BoundingBox(-400, -400, -400, 800, 800, 800);
    private static BoundingBox schwarmHimmel;

    private double mousePositionX;
    private double mousePositionY;

    private long timeOfLastFrame = 0L;
    private long startZeit;
    private Iterator<Schwarm3d.SchwarmKonfiguration> konfigurationen;

    private final PhongMaterial vogelMaterial = new PhongMaterial();
    private final PhongMaterial ameiseMaterial = new PhongMaterial();
    private final PhongMaterial vogelZufaelligeBewegungsMaterial = new PhongMaterial();
    private final PhongMaterial ameiseZufaelligeBewegungsMaterial = new PhongMaterial();

    /**
     * Konstruktor in dem die Liste aller gezeigten Simulationskonfigurationen erstellt wird.
     * History Constraint: JavaFX wird auf der ausfuehrenden Maschine unterstuetzt und Applikation3d wurde ueber JavaFX mittels launch initialisiert
     */
    public Applikation3d() {
        super();
        BoundingBox himmel = new BoundingBox(0, 0, 0, 400, 400, 400);
        List<Schwarm3d.SchwarmKonfiguration> konfigurationen = new ArrayList<Schwarm3d.SchwarmKonfiguration>(3);
        int generelleSimulationsDauer = 12;
        int generelleZufaelligeBewegungsdauer = 3;
        konfigurationen.add(new Schwarm3d.SchwarmKonfiguration(200, 100, 5, 3, 5, 5, himmel, generelleSimulationsDauer, generelleZufaelligeBewegungsdauer));
        konfigurationen.add(new Schwarm3d.SchwarmKonfiguration(300, 200, 10, 2, 5, 10, himmel, generelleSimulationsDauer, generelleZufaelligeBewegungsdauer));
        konfigurationen.add(new Schwarm3d.SchwarmKonfiguration(300, 200, 20, 1, 50, 1, himmel, generelleSimulationsDauer, generelleZufaelligeBewegungsdauer));

        this.konfigurationen = konfigurationen.iterator();
    }

    /**
     * Erstellt die Kamera und positioniert sie fuer einen guten Ueberblick auf die Simulation
     *
     * VB: Applikation3d wurde ueber JavaFX Container gestartet
     *     kamera3d RotationsAchsen != null
     * NB: Kamera dem Szenengraphen hinzugefuegt und positioniert
     */
    private void erstelleKamera() {
        root.getChildren().add(kamera3d);
        kamera3d.getChildren().add(kamera);
        kamera.setNearClip(KAMERA_NAH);
        kamera.setFarClip(KAMERA_FERN);
        kamera.setTranslateZ(KAMERA_DISTANZ);
        // Verschiebe Kamera fuer besseren Ueberblick
        kamera3d.setTranslateY(80);
        kamera3d.getRotationXAchse().setAngle(WINKEL_X);
        kamera3d.getRotationYAchse().setAngle(WINKEL_Y);
    }

    /**
     * Erstellt die grafische Darstellung der Waende innerhalb derer sich die Voegel bewegen werden.
     *
     * VB: Applikation3d wurde ueber JavaFX Container gestartet
     *     visualisierungsHimmel width & height >= 0
     * NB: Waende werden dem Szenengraph hinzugefuegt
     */
    private void erstelleWaende() {
        PhongMaterial rot = new PhongMaterial();
        rot.setDiffuseColor(Color.DARKRED);
        rot.setSpecularColor(Color.RED);

        PhongMaterial gruen = new PhongMaterial();
        gruen.setDiffuseColor(Color.DARKGREEN);
        gruen.setSpecularColor(Color.GREEN);

        PhongMaterial blau = new PhongMaterial();
        blau.setDiffuseColor(Color.DARKBLUE);
        blau.setSpecularColor(Color.BLUE);


        // JavaFX verwendet folgendes Koordinatensystem:
        // X-Achse von links (kleine Werte) nach rechts (große Werte)
        // Y-Achse von oben (kleine Werte) nach unten (große Werte)
        // Z-Achse von nah zur Kamera(kleine Werte) nach fern zur Kamera (große Werte)
        // siehe https://docs.oracle.com/javase/8/javafx/graphics-tutorial/camera.htm
        final Box xAchse = new Box(visualisierungsHimmel.getWidth(), visualisierungsHimmel.getHeight(), 1);
        xAchse.setDrawMode(DrawMode.LINE);
        // Positioniere X-Achse ganz weit weg am Himmel
        xAchse.setTranslateZ(visualisierungsHimmel.getMaxZ());
        final Box yAchse = new Box(1, visualisierungsHimmel.getHeight(), visualisierungsHimmel.getDepth());
        yAchse.setDrawMode(DrawMode.LINE);
        // Positioniere Y-Achse ganz weit links am Himmel
        yAchse.setTranslateX(visualisierungsHimmel.getMinX());
        final Box zAchse = new Box(visualisierungsHimmel.getWidth(), 1, visualisierungsHimmel.getDepth());
        zAchse.setDrawMode(DrawMode.LINE);
        // Positioniere Z-Achse ganz weit unten am Himmel
        zAchse.setTranslateY(visualisierungsHimmel.getMaxY());
        xAchse.setMaterial(rot);
        yAchse.setMaterial(gruen);
        zAchse.setMaterial(blau);

        waende.getChildren().addAll(xAchse, yAchse, zAchse);
        waende.setVisible(true);
        world.getChildren().addAll(waende);
    }

    /**
     * Erstellt die InputListener zur Steuerung der Kamera.
     * Die Kamera laesst sich drehen und man kann raus und reinzoomen.
     *
     * VB: Scene != null
     * NB: der Scene wurden Mouse-Input Listener hinzugefuegt
     *
     * @param scene - Scene auf der die User-Inputs getriggert werden
     */
    private void erstelleInputListener(Scene scene) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePositionX = event.getSceneX();
                mousePositionY = event.getSceneY();
            }
        });
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double scroll = event.getDeltaY();
                double z = kamera.getTranslateZ();
                double newZ = z + scroll;
                kamera.setTranslateZ(newZ);
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    double mousePositionXVorBewegung = mousePositionX;
                    double mousePositionYVorBewegung = mousePositionY;
                    mousePositionX = event.getSceneX();
                    mousePositionY = event.getSceneY();
                    kamera3d.getRotationXAchse().setAngle(kamera3d.getRotationXAchse().getAngle() - (mousePositionY - mousePositionYVorBewegung));
                    kamera3d.getRotationYAchse().setAngle(kamera3d.getRotationYAchse().getAngle() + (mousePositionX - mousePositionXVorBewegung));
                }
            }
        });
    }

    /**
     * Erstellt die grafische Darstellung der Voegel und Ameisen und initialisiert den Schwarm
     *
     * VB: Schwarmkonfiguration konfig != null
     * NB: Initialisierte Schwarm Simulation und Initialisierte grafische Objekte fuer die Darstellung der Tierarten
     *
     * @param konfig SchwarmKonfiguration anhand der der Schwarm erstellt werden soll.
     */
    private void erstelleSchwarm(Schwarm3d.SchwarmKonfiguration konfig) {
        vogelListe = new ArrayList<>();
        ameisenListe = new ArrayList<>();

        schwarm = new Schwarm3d(konfig);
        schwarmHimmel = konfig.getHimmel();
        vogelMaterial.setDiffuseColor(Color.RED);
        vogelMaterial.setSpecularColor(Color.RED);
        for (int i = 0; i < schwarm.getVoegel().size(); i++) {
            Box b = new Box(5, 5, 5);
            b.setMaterial(vogelMaterial);
            b.setTranslateX(schwarm.getVoegel().get(i).getPosition().getX());
            b.setTranslateY(schwarm.getVoegel().get(i).getPosition().getY());
            b.setTranslateZ(schwarm.getVoegel().get(i).getPosition().getZ());
            vogelListe.add(b);
            agents.getChildren().add(b);
        }

        ameiseMaterial.setDiffuseColor(Color.BLUE);
        ameiseMaterial.setSpecularColor(Color.BLUE);
        for (int i = 0; i < schwarm.getAmeisen().size(); i++) {
            Sphere a = new Sphere(3);
            a.setMaterial(ameiseMaterial);
            a.setTranslateX(schwarm.getAmeisen().get(i).getPosition().getX());
            a.setTranslateY(schwarm.getAmeisen().get(i).getPosition().getY());
            a.setTranslateZ(schwarm.getAmeisen().get(i).getPosition().getZ());
            ameisenListe.add(a);
            agents.getChildren().add(a);
        }

        // Voegel die sich zufaellig bewegen, blinken.
        vogelZufaelligeBewegungsMaterial.setDiffuseColor(Color.GREEN);
        vogelZufaelligeBewegungsMaterial.setSpecularColor(Color.GREEN);
        // Ameisen die sich zufaellig bewegen, blinken.
        ameiseZufaelligeBewegungsMaterial.setDiffuseColor(Color.BROWN);
        ameiseZufaelligeBewegungsMaterial.setSpecularColor(Color.BROWN);

        world.getChildren().addAll(agents);
    }

    /**
     * Beende die Applikation.
     *
     * VB: die Applikation laeuft
     * NB: die Applikation wurde gestoppt
     */
    public void stoppeApplikation() {
        try {
            stop();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Fehler beim Beenden der Applikation!");
            e.printStackTrace();
        }
    }

    /**
     * Andernfalls wird die Applikation beendet.
     * Erzeugt eine neue Simulation, sollte es noch eine nicht gezeigte Konfiguration geben.
     *
     * VB: Konfigurationen fuer Simulation vorhanden
     * NB: wenn alle Konfigurationen simuliert, wird Applikation beendet
     *     wenn weitere Konfigurationen vorhanden sind, wird eine neue Simulation gestartet.
     */
    private void erzeugeNeueSimulation() {
        if(!konfigurationen.hasNext()) {
            stoppeApplikation();
        }else {
            System.out.println("Erzeuge neue Simulation mit anderen Konfigurationsparametern");
            agents.getChildren().removeAll(ameisenListe);
            agents.getChildren().removeAll(vogelListe);
            world.getChildren().removeAll(agents);

            erstelleSchwarm(konfigurationen.next());
        }
    }

    /**
     * "Einstiegspunkt" der Applikation. Wird vom JavaFx Container aufgerufen, nachdem die Applikation ueber launch(..) gestartet wurde.
     * Initialisiert alle noetigen Komponenten und erzeugt den AnimationTimer, der vor jedem Frame-Refresh getriggert wird.
     * Im AnimationTimer wird der Simulationsschritt gestartet und die grafische Darstellung der Tiere im Szenegraphen auf die neuen Positionen aktualisiert.
     *
     * VB: Applikation3d bzw. root wurden ordnungsgemaess initialisiert; stage != null
     * NB: Grafische Oberflaeche wird angezeigt und die Simulation wird gestartet.
     * GOOD: History Constraints von Schwarm3d werden eingehalten
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        erstelleKamera();
        erstelleWaende();

        Scene scene = new Scene(root, 800, 800, true);
        scene.setFill(Color.WHITE);
        erstelleInputListener(scene);
        scene.setCamera(kamera);

        world.getChildren().add(new AmbientLight(Color.WHITE));

        stage.setTitle("Schwarm - Simulation 3d");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        Schwarm3d.SchwarmKonfiguration konfig = konfigurationen.next();
        erstelleSchwarm(konfig);
        startZeit = System.nanoTime();

        AnimationTimer timer = new AnimationTimer() {
            /**
             * VB: now >= 0
             * VB: handle(...) wird 60 mal in der Sekunde aufgerufen
             * NB: Simulationsschritt wurde ausgefuehrt und die Position der Tiere aktualisiert.
             * INVARIANTEN: Maximal 60 Simulationsschritte pro Sekunde
             * */
            long letzteNeueZufaelligeBewegung = startZeit;
            @Override
            public void handle(long now) {
                long endZeit = startZeit + Util.secondsToNanoseconds(konfig.getSimulationsDauer());

                if(System.nanoTime() > endZeit) {
                    erzeugeNeueSimulation();
                    startZeit = System.nanoTime();
                }

                // update simulation and visual representation 60 times per second at most
                // 60 FPS are 16,666,666.666... nanoseconds
                // Limitierung auf 60 FPS noetig, da sie in Java 8 unter gewissen Vorraussetzungen nicht im AnimationTimer eingehalten wird
                // https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8211059
                if (now - timeOfLastFrame < 16666666L) {
                    return;
                } else {
                    // wenn zufaelligeBewegungsDauer Sekunden vergangen sind, werden neue Agents ausgewaehlt, die sich zufaellig bewegen.
                    boolean waehleNeueAgentsFuerZufaelligeBewegung = (now - letzteNeueZufaelligeBewegung) >= Util.secondsToNanoseconds(konfig.getZufaelligeBewegungsDauer());
                    if (waehleNeueAgentsFuerZufaelligeBewegung) {
                        letzteNeueZufaelligeBewegung = now;
                    }

                    schwarm.simuliereEinenSchritt(waehleNeueAgentsFuerZufaelligeBewegung);

                    // aktualisiere Position der Voegel
                    for (int i = 0; i < schwarm.getVoegel().size() && i < vogelListe.size(); i++) {
                        Vogel vogel = schwarm.getVoegel().get(i);
                        Point3D vogelPosition = vogel.getPosition();

                        // skaliere Position von schwarmHimmel (nur ganzzahle positive Werte) auf visualisierungsHimmel (double Werte)
                        double xPosition = visualisierungsHimmel.getMinX() + vogelPosition.getX() / (double)schwarmHimmel.getWidth() * (visualisierungsHimmel.getMaxX() - visualisierungsHimmel.getMinX());
                        double yPosition = visualisierungsHimmel.getMinY() + vogelPosition.getY() / (double)schwarmHimmel.getHeight() * (visualisierungsHimmel.getMaxY() - visualisierungsHimmel.getMinY());
                        double zPosition = visualisierungsHimmel.getMinZ() + vogelPosition.getZ() / (double)schwarmHimmel.getDepth() * (visualisierungsHimmel.getMaxZ() - visualisierungsHimmel.getMinZ());

                        Box vogelBox = vogelListe.get(i);
                        if (!vogel.bewegtSichZufaellig()) {
                            vogelBox.setMaterial(vogelMaterial);
                        } else {
                            // Voegel die sich zufaellig bewegen werden anders dargestellt.
                            vogelBox.setMaterial(vogelZufaelligeBewegungsMaterial);
                        }

                        vogelBox.setTranslateX(xPosition);
                        vogelBox.setTranslateY(yPosition);
                        vogelBox.setTranslateZ(zPosition);
                    }
                    // aktualisiere Position der Ameisen
                    for (int i = 0; i < schwarm.getAmeisen().size() && i < ameisenListe.size(); i++) {
                        Ameise ameise = schwarm.getAmeisen().get(i);
                        Point3D ameisePosition = ameise.getPosition();

                        double xPosition = visualisierungsHimmel.getMinX() + ameisePosition.getX() / (double) schwarmHimmel.getWidth() * (visualisierungsHimmel.getMaxX() - visualisierungsHimmel.getMinX());
                        double zPosition = visualisierungsHimmel.getMinZ() + ameisePosition.getZ() / (double) schwarmHimmel.getWidth() * (visualisierungsHimmel.getMaxZ() - visualisierungsHimmel.getMinZ());

                        Sphere ameisenSphere = ameisenListe.get(i);
                        if (!ameise.bewegtSichZufaellig()) {
                            ameisenSphere.setMaterial(ameiseMaterial);
                        } else {
                            // Ameisen die sich zufaellig bewegen werden anders dargestellt.
                            ameisenSphere.setMaterial(ameiseZufaelligeBewegungsMaterial);
                        }

                        ameisenListe.get(i).setTranslateX(xPosition);
                        ameisenListe.get(i).setTranslateZ(zPosition);
                    }

                    timeOfLastFrame = now;
                }
            }
        };
        timer.start();
    }

}
