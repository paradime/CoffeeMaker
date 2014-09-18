import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.Recipe;
import junit.framework.TestCase;


public class CoffeeMakerTest extends TestCase {

	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	
	protected void setUp() throws Exception {
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r1
		r2 = new Recipe();
		r2.setName("Coffee");
		r2.setAmtChocolate("0");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddRecipeIfNoneInSystem(){		
		assertTrue(cm.addRecipe(r1));
	}
	public void testAddRecipeIfThreeInSystem(){		
		cm.addRecipe(r1);
		cm.addRecipe(r1);
		cm.addRecipe(r1);
		assertFalse(cm.addRecipe(r1));
	}
	
	public void testAddRecipePriceNotInteger() throws Exception{
		try{
			r2.setPrice("fiftyDollars");
			fail("ExceptionMustBeThrown");
		}catch(Exception ex){
			
		}
		
	}
	
	public void testDeleteRecipeThrowsErrorWhenOutofBoundsGiven(){
		try{
			cm.deleteRecipe(5);
			fail("ExceptionMustBeThrown");
		}catch(Exception ex){
			
		}
	}
	
}
