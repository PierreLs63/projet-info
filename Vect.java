
public class Vect {
    public double x;
    public double y;

    public Vect (double ax, double ay){
        x = ax;
        y = ay;
    }

    public double norm(){
        return Math.sqrt(x*x+y*y);
    }

    public Vect times (double m){
        return new Vect (m*x, m*y);
    }

    public void add (Vect v){
        x = x+v.x;
        y = y+v.y;
    }

    public Vect vectAdd (Vect v){
        return new Vect (x+v.x, y+v.y);
    }

    public void normalize (){
        double a = this.norm();
        x = x/a;
        y = y/a;
    }

    public Vect normalized() {
        return new Vect(this.x/this.norm(), this.y/this.norm());
    }

    public double angle() {
        return Math.atan2(this.y, this.x) ;
    }

    public double distance(double vx, double vy){
        double distance;
        Vect DistanceV = new Vect (x-vx, y-vy);
        distance = DistanceV.norm();
        return distance;
    }

    public boolean equals (Vect v){
        boolean test = false;
        if (v.x == x && v.y == y){
            test = true;
        }
        return test;
    }

}

