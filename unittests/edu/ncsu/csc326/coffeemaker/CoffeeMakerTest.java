package edu.ncsu.csc326.coffeemaker;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import sun.misc.IOUtils;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import junit.framework.TestCase;

/**
 * 
 * @author Sarah Heckman
 *
 * Unit tests for CoffeeMaker class.
 */
public class CoffeeMakerTest extends TestCase {
	
	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

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
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
		super.setUp();
	}
	
	public void testAddInventory() {
		try {
			cm.addInventory("4","7","0","9");
		} catch (InventoryException e) {
			fail("InventoryException should not be thrown");
		}
	}
	
	public void testAddInventoryException() {
		try {
			cm.addInventory("4", "-1", "asdf", "3");
			fail("InventoryException should be thrown");
		} catch (InventoryException e) {
			//success if thrown
		}
	}
	
	public void testAddInventoryChocNegNumbersException() throws Exception{
		try {
			cm.addInventory("1", "1", "1", "-1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testAddInventoryChocWordsException() throws Exception{
		try {
			cm.addInventory("1", "1", "1", "asdf");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
			assertTrue(true);
		}
	}
	
	public void testAddInventoryCoffNegNumbersException() throws Exception{
		try {
			cm.addInventory("-1", "1", "1", "1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testAddInventoryCoffWordsException() throws Exception{
		try {
			cm.addInventory("asdf", "1", "1", "1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
			assertTrue(true);
		}
	}
	
	public void testAddInventoryMilkfNegNumbersException() throws Exception{
		try {
			cm.addInventory("1", "-1", "1", "1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testAddInventoryMilkWordsException() throws Exception{
		try {
			cm.addInventory("1", "asdf", "1", "1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
			assertTrue(true);
		}
	}
	
	public void testAddInventorySugarfNegNumbersException() throws Exception{
		try {
			cm.addInventory("1", "1", "-1", "1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testAddInventorySugarWordsException() throws Exception{
		try {
			cm.addInventory("1", "1", "asdf", "1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
			assertTrue(true);
		}
	}
	//Currently Fails because Sugar isn't being properly added
	public void testCheckInventoryDisplaysAllInventory(){
		try{
			cm.addInventory("4","7","1","9");
		}catch (Exception ex){
			
		}
		String text = cm.checkInventory();
		assertEquals("Coffee: 19\nMilk: 22\nSugar: 16\nChocolate: 24\n", text);
	}
	
	public void testMakeCoffee() {
		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));
	}
	
	public void testAddRecipeIfNoneInSystem(){		
		assertTrue(cm.addRecipe(r1));
	}
	
	//Currently fails because you CAN add more than 3 but you shouldn't
	public void testAddRecipeIfThreeInSystem(){		
		cm.addRecipe(r1);
		cm.addRecipe(r2);
		cm.addRecipe(r3);
		assertFalse(cm.addRecipe(r4));
	}
	
	public void testAddRecipePriceNotIntegerThrowsExcpetion() throws Exception{
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

	
	public void testDeleteRecipeReturnsNameWhenRecipeExists(){
		//Arrange
		cm.addRecipe(r1);
		//Act
		String name = cm.deleteRecipe(0);
		//Assert
		assertTrue(name == r1.getName());
		
	}
	
	//Currently fails because it returns ""
	public void testEditRecipeReturnsCorrectNameWhenRecipeExists(){
		//Arrange
		cm.addRecipe(r1);
		//Actf
		String name = cm.editRecipe(0, r2);
		//Assert
		assertTrue(name == cm.getRecipes()[0].getName());
		
	}
	
	public void testEditRecipeReturnsCorrectRecipeWhenRecipeExists(){
		//Arrange
		cm.addRecipe(r1);
		//Act
		cm.editRecipe(0, r2);
		//Assert
		assertTrue(r2.getAmtChocolate() == cm.getRecipes()[0].getAmtChocolate());
		assertTrue(r2.getAmtCoffee() == cm.getRecipes()[0].getAmtCoffee());
		assertTrue(r2.getAmtSugar() == cm.getRecipes()[0].getAmtSugar());
		assertTrue(r2.getAmtMilk() == cm.getRecipes()[0].getAmtMilk());
	}
	
	public void testMakeCoffeeReturnsCorrectChangeIfNotEnoughMoney(){
		//Arrange
		cm.addRecipe(r1);
		//Act
		int change = cm.makeCoffee(0, 49);
		//Assert
		assertEquals(49, change);
	}
	
	public void testMakeCoffeeReturnsCorrectChangeIfNotEnoughIngredients(){
		//Arrange
		cm.addRecipe(r2);
		//Act
		int change = cm.makeCoffee(0, 75);
		//Assert
		assertEquals(75, change);
	}
	
	public void testMakeCoffeeReturnsCorrectChangeIfEverythingGood(){
		//Arrange
		cm.addRecipe(r1);
		//Act
		int change = cm.makeCoffee(0, 75);
		//Assert
		assertEquals(25, change);
	}
	
	public void testHashCode(){
		r1.hashCode();
		assertTrue(true);
	}

	public void testEqualitySameObj(){
		assertTrue(r1.equals(r1));
	}
	
	public void testEqualityDiffObj(){
		assertFalse(r1.equals(new Integer(1)));
	}
	
	public void testEqualityDiffName(){
		Recipe r6 = new Recipe();
		r6.setName(null);
		r6.toString();
		assertFalse(r6.equals(r1));
	}
	
	public void testRecipeSetChocNegNumbersException() throws Exception{
		try {
			r1.setAmtChocolate("-1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testRecipeSetChocAlphaException() throws Exception{
		try {
			r1.setAmtChocolate("abc");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testRecipeSetMilkNegNumbersException() throws Exception{
		try {
			r1.setAmtMilk("-1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testRecipeSetMilkcAlphaException() throws Exception{
		try {
			r1.setAmtMilk("abc");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testRecipeSetSugarAlphaException() throws Exception{
		try {
			r1.setAmtSugar("abc");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	public void testRecipeSetSugarNegNumberException() throws Exception{
		try {
			r1.setAmtSugar("-1");
			fail("InventoryException should be thrown");
		} catch (Exception e) {
			//success if thrown
		}
	}
	
	
}
