package rest.domain;
import java.util.Objects;

public class Probe {

    private  Long id ;

    public Probe(Long id, Distanta dst, Stil st) {
        this.id = id;
        this.dst = dst;
        this.st = st;
    }

    @Override
    public String toString() {
        return "Probe{" +
                "id=" + id +
                ", dst=" + dst +
                ", st=" + st +
                '}';
    }

    private Distanta dst;
    private Stil st ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Probe probe)) return false;
        return Objects.equals(getId(), probe.getId()) && getDst() == probe.getDst() && getSt() == probe.getSt();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDst(), getSt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Probe() {

    }

    public Distanta getDst() {
        return dst;
    }

    public void setDst(Distanta dst) {
        this.dst = dst;
    }

    public Stil getSt() {
        return st;
    }

    public void setSt(Stil st) {
        this.st = st;
    }
}
