package Bilist;

public abstract class Vehicle {
  protected Driver driver;
  protected int remainingMiles;
  protected int milesPerDrive;
  protected String vehicleName;
  protected int ageLimit;

  public Vehicle(int remainingMiles, int milesPerDrive, int ageLimit, String name) {
    this.remainingMiles = remainingMiles;
    this.milesPerDrive = milesPerDrive;
    this.ageLimit = ageLimit;
    this.vehicleName = name;
    this.driver = null;

    System.out.printf("%s created. %d miles to go! %n", name, remainingMiles);
  }

  public void setDriver(Driver driver) {
    int driverAge = driver.getAge();
    String driverName = driver.getName();

    if (driverAge < ageLimit) {
      System.out.printf("Driver not changed! %s is %d but must be %d or older to drive %s %n", driverName, driverAge, ageLimit, vehicleName);
    } else {
      this.driver = driver;
      System.out.printf("Driver changed to %s %n", driverName);
    }
  }

  public void drive() {
    if (driver == null) {
      System.out.printf("%s didn't drive - there's no driver! %n", vehicleName);
    } else {
      remainingMiles -= milesPerDrive;
      System.out.printf("%s drove %d miles - %d miles to go %n", vehicleName, milesPerDrive, remainingMiles);
    }
  }

}
