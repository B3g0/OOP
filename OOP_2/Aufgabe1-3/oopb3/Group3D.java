package oopb3;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * Erweiterung der Klasse Group um vordefinierte Rotationen um die jeweiligen Achsen.
 * Hilfsklasse um die Kamerasteuerung einfacher zu gestalten.
 */
public class Group3D extends Group {

    private Rotate rotationXAchse = new Rotate();
    private Rotate rotationYAchse = new Rotate();
    private Rotate rotationZAchse = new Rotate();

    public Group3D() {
        super();
        rotationXAchse.setAxis(Rotate.X_AXIS);
        rotationYAchse.setAxis(Rotate.Y_AXIS);
        rotationZAchse.setAxis(Rotate.Z_AXIS);
        getTransforms().addAll(rotationZAchse, rotationYAchse, rotationXAchse);
    }

    public Rotate getRotationXAchse() {
        return rotationXAchse;
    }

    /**
     * NB: Rotation X Achse gesetzt
     * @param rotationXAchse
     */
    public void setRotationXAchse(Rotate rotationXAchse) {
        this.rotationXAchse = rotationXAchse;
    }

    public Rotate getRotationYAchse() {
        return rotationYAchse;
    }

    /**
     * NB: Rotation Y Achse gesetzt
     * @param rotationYAchse
     */
    public void setRotationYAchse(Rotate rotationYAchse) {
        this.rotationYAchse = rotationYAchse;
    }

    public Rotate getRotationZAchse() {
        return rotationZAchse;
    }

    /**
     * NB: Rotation Z Achse gesetzt
     * @param rotationZAchse
     */
    public void setRotationZAchse(Rotate rotationZAchse) {
        this.rotationZAchse = rotationZAchse;
    }
}
