package net.darkaqua.blacksmith.api.util;

/**
 * Created by cout970 on 28/11/2015.
 */
public class Vect4d {

    protected double x;
    protected double y;
    protected double z;
    protected double w;

    public Vect4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public Vect4d set(double x, double y, double z, double w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Vect4d add(double x, double y, double z, double w){
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public Vect4d getOpposite(){
        return new Vect4d(-x, -y , -z , -w);
    }

    public Vect4d multiply(double a){
        this.x *= a;
        this.y *= a;
        this.z *= a;
        this.w *= a;
        return this;
    }

    public Vect4d copy(){
        return  new Vect4d(x, y, z, w);
    }
}
