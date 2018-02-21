package org.academiadecodigo.haltistas.trumpvslatinos.gameObjects;

public class Trump extends GameObject implements Shooter {

    private Direction direction;
    private boolean shooting;
    private Paper[] paper;


    public Trump(int x, int y, String source) {
        super(x, y, source);
        this.direction = Direction.STAY;
        this.paper = new Paper[10];
    }

    public void move() {
        if (direction.equals(Direction.RIGHT)) {
            translate(10, 0);
            direction = Direction.STAY;
            System.out.println("trump m right");
            return;
        }

        if (direction.equals(Direction.LEFT)) {
            translate(-10, 0);
            direction = Direction.STAY;
            System.out.println("trump m left");
            return;
        }

        translate(0, 0);

    }


    public Paper shoot() {

        if (shooting) {
            System.out.println("is shooting");
            int paperWidth = 20;
            shooting = false;
            for (int i = 0; i < paper.length; i++) {
                if (paper[i] == null) {
                    return paper[i] = new Paper(getX() + getWidth() - paperWidth, 700, "assets/paper.png");
                }

            }

        }
        return null;
    }

    @Override
    public void translate(double dx, double dy) {

        int currentX = getX();

        if (currentX == 510 && dx > 0) {
            return;
        }

        if (currentX == 10 && dx < 0) {
            return;
        }

        setX(currentX + (int) dx);
        getPicture().translate(dx, dy);

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setShooting(boolean bool) {
        this.shooting = bool;
    }

    public void setPaper(int position) {
        paper[position] = null;
    }

    public void paperShoot() {
        for (int i = 0; i < paper.length; i++) {

            if (paper[i] == null) {
                paper[i] = shoot();

            }
        }

        for (int i = 0; i < paper.length; i++) {

            if (paper[i] != null) {
                System.out.println("paper must be moving");
                paper[i].move(); //adicionar à lista
                if (paper[i].getY() == 10) {
                    paper[i] = null;
                    setPaper(i);
                }
            }

        }

    }
}

