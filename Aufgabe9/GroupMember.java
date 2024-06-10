
public enum GroupMember {
    Christoph,
    Tobias,
    Mensur;

    @Override
    public String toString() {
        return String.format("%s",this.name());
    }
}
