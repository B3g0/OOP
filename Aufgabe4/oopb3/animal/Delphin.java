package oopb3.animal;

import oopb3.animal.abstr.SchoolAnimal;

import java.util.List;

/**
 * Delphine leben in einer Schule mit anderen sozialen SchoolAnimals
 */
public class Delphin extends SchoolAnimal {

    private List<SchoolAnimal> school;

    /**
     * @param school - die zum Delphin zugehoerige Schule von SchoolAnimals
     */
    public Delphin(List<SchoolAnimal> school) {
        this.school = school;
    }

    /**
     * Delphin ohne Schule im Konstruktor
     */
    public Delphin() {
    }

    @Override
    public int social() {
        return getErwarteteLebenszeit();
    }

    @Override
    public int air() {
        return 0;
    }

    @Override
    public int water() {
        return getErwarteteLebenszeit();
    }

    @Override
    public int ground() {
        return 0;
    }

    public List<SchoolAnimal> getSchool() {
        return school;
    }

    public void setSchool(List<SchoolAnimal> school) {
        this.school = school;
    }
}
