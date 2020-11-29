package pizzeria;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pizza.Pizza;
import pizzeria.factory.PizzeriaFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestPizzeriaTestDrive {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

// Test code stolen from Kami Saitov, Innopolis University Student -- F20
	@Test
	public void pizzaTestDriveTest() {
		Pizzeria shopFromBrest = PizzeriaFactory.getInstance().create("Brest");
		Pizzeria shopFromStrasbourg = PizzeriaFactory.getInstance().create("Strasbourg");

		Pizza pizza = shopFromBrest.orderPizza("cheese");
		System.out.println("JMB has ordered a " + pizza.getName());

		pizza = shopFromStrasbourg.orderPizza("cheese");
		System.out.println("JMI has ordered a " + pizza.getName());

		String expected = "Preparation of Pizza with Brest style sauce and cheese\n" + "Spread the pizza dough...\n"
				+ "Add the sauce...\n" + "Add the garnitures:\n" + "Parmigiano reggiano\n"
				+ "Bake 25 minutes at 180 degrees\n" + "Cut the pizza in triangles\n"
				+ "Put the pizza in the official box\n" + "JMB has ordered a Pizza with Brest style sauce and cheese\n"
				+ "Preparation of Pizza Strasbourg style with cheese\n" + "Spread the pizza dough...\n"
				+ "Add the sauce...\n" + "Add the garnitures:\n" + "Mozzarella\n" + "Bake 25 minutes at 180 degrees\n"
				+ "Cut in square portions\n" + "Put the pizza in the official box\n"
				+ "JMI has ordered a Pizza Strasbourg style with cheese\n" + "\n" + "";

		assertEquals(expected.trim(), outContent.toString().trim());
	}

	@Test
	public void checkPizzasFromDifferentPizzeriasAreDifferent() {
		Pizzeria strasbourg = PizzeriaFactory.getInstance().create("Strasbourg");
		Pizzeria brest = PizzeriaFactory.getInstance().create("Brest");

		Pizza pizza1 = strasbourg.orderPizza("cheese");
		Pizza pizza2 = brest.orderPizza("cheese");

		assertNotEquals(pizza1, pizza2);
	}

	@Test
	public void orderPizza() {
		Pizzeria strasbourg = PizzeriaFactory.getInstance().create("Strasbourg");
		Pizza pizza1 = strasbourg.orderPizza("cheese");

		assertNotEquals(pizza1, null);
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
}