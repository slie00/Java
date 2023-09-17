public class Ghost {

    private String name;
    private double aura;
    private boolean materialized;

    public Ghost(String name) {
        this.name = name;
    }

    public Ghost(String name, double aura) {
        this(name);

        this.aura = aura;
    }

    public Ghost(String name, double aura, boolean materialized) {
        this(name, aura);

        this.materialized = materialized;
    }

    public String getName() {
        return name;
    }

    public double getAura() {
        return aura;
    }

    public void setAura(double aura) {
        this.aura = aura;
    }

    public boolean isMaterialized() {
        return materialized;
    }

    public void setMaterialized(boolean materialized) {
        this.materialized = materialized;
    }

    public boolean isGood() {
        return aura > 0;
    }

    public boolean isEvil() {
        return aura < 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;

        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Ghost)) {
            return false;
        }

        Ghost other = (Ghost) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        return true;
    }

}
