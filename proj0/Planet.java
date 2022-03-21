import java.lang.Math;

public class Planet {
    // Data member
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    // Constructor
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // Copy constructor
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    // Calculator two planet distance
    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Calculator two planet force
    double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return (G * mass * p.mass / (r * r));
    }

    // Calculator two planet force by X
    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - xxPos;
        return (dx / r * f);
    }

    // Calculator two planet force by Y
    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - yyPos;
        return (dy / r * f);
    }

    // Calculator net force by X
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double f = 0.0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                f += calcForceExertedByX(p);
            }
        }

        return f;
    }

    // Calculator net force by X
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double f = 0.0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                f += calcForceExertedByY(p);
            }
        }

        return f;
    }

    // Calculator update
    public void update(double dt, double fX, double fY) {
        // Calculator acceleration
        double ax = fX / mass;
        double ay = fY / mass;
        // Update velocity
        xxVel += dt * ax;
        yyVel = dt * ay;
        // Update position
        xxVel += xxVel * dt;
        yyVel += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
