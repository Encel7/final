package by.epam.shop.entity;

public class BasicEntity {

    private Integer identity;

    public BasicEntity(Integer identity) {
        this.identity = identity;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj != this) {
                if (obj.getClass() == getClass() && identity != null) {
                    return identity.equals(((BasicEntity) obj).identity);
                }
                return false;
            }
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return identity.hashCode();
    }


}
