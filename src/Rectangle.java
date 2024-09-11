public class Rectangle {
  private int x, y, width, height;

  public Rectangle() {
    x = 0;
    y = 0;
    width = 0;
    height = 0;
  }

  public Rectangle(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public Rectangle(int width, int height, int x, int y) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }

  public double calculateArea() {
    return width * height;
  }

  public double calculatePerimiter() {
    return (width * 2) + (height * 2);
  }

  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean checkCollision(Rectangle rectangle) {

    return (
//      Är vänstra kanten av rek1 är till vänster om den högra kanten av rek2
        this.x < rectangle.x  + rectangle.width &&
//      Är den högra kanten av rek1 till höger om den vänstra kanten av rek2
        this.x + this.width   > rectangle.x &&

//      Är den översta kanten på rek1 under den understa kanten av rek2
        this.y < rectangle.y  + rectangle.height &&
//      Är den understa kanten på rek1 över den översta kanten av rek2
        this.y + this.height  > rectangle.y
    );
  }

}
