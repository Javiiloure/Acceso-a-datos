
public class Car {
	public static int count = 1;
	String id;
	String brand;
	String color;
	String plate;
	
	public Car() {
		this.id = String.valueOf(count);
		count++;
		this.brand = "";
		this.color = "";
		this.plate = "";
	}
	
	public Car(String brand, String color, String plate) {
		this.id = String.valueOf(count);
		count++;
		this.brand = brand;
		this.color = color;
		this.plate = plate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", color=" + color + ", plate=" + plate + "]";
	}
	
}
