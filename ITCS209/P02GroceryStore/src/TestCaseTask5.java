

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

/**
 * Implements Loggable interface in Item, Customer, EWallet, and Order
 * Testing log method in those classes
 * The log method is used in the Data class to write content to the data log files.
 * 
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestCaseTask5 {

	double e = 1.0;	// epsilon
	String SAMPLE_PATH = Paths.get("").toAbsolutePath().toString() 
			+ File.separator + "testcase_log" +  File.separator;

	String STOCK_FILE = SAMPLE_PATH + "stocks.txt";
	String CUSTOMER_FILE = SAMPLE_PATH + "customers.txt";
	String WALLET_FILE = SAMPLE_PATH + "wallets.txt";
	String ORDER_FILE = SAMPLE_PATH + "orders.txt";

	
	@Test
	public void test0DeleteLogFiles() {
		DataManagement.removeLogFile(STOCK_FILE);
		DataManagement.removeLogFile(CUSTOMER_FILE);
		DataManagement.removeLogFile(WALLET_FILE);
		DataManagement.removeLogFile(ORDER_FILE);
	}

	@Test
	public void test1StoreItems() {
		
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("cleaner", 30.0, false));
		items.add(new Item("butter", 460.00, false, 20));
		items.add(new Item("cocoa", 320.00, true));
		items.add(new Item("coffee", 190.00, false, 2));
		items.add(new Item("cereals", 440.00, true));
		
		assertEquals("cleaner,30.00,false,2147483647", items.get(0).log());
		assertEquals("butter,460.00,false,20", items.get(1).log());
		assertEquals("cocoa,320.00,true,2147483647", items.get(2).log());
		assertEquals("coffee,190.00,false,2", items.get(3).log());
		assertEquals("cereals,440.00,true,2147483647", items.get(4).log());

		DataManagement.storeItems(this.STOCK_FILE, false, items);
		// If you cannot implement task 2 and task 4,
		// you can also compare your output file (testcase_log/stocks.txt) 
		// with the expected output file (testcase_log/stocks_expected.text)
	}
	
	@Test
	public void test2StoreCustomers() {
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("Andrew"));
		customers.add(new CustomerOnline("Bob", 50.0));
		customers.add(new CustomerOnline("Charis", 200.0));
		customers.add(new CustomerOnline(5, "Ema", 100.0));
		
		assertEquals("1,Andrew", customers.get(0).log());
		assertEquals("2,Bob,50.00", customers.get(1).log());
		assertEquals("3,Charis,200.00", customers.get(2).log());
		assertEquals("5,Ema,100.00", customers.get(3).log());
		
		DataManagement.storeCustomers(this.CUSTOMER_FILE, false, customers);
		// If you cannot implement task 2 and task 4,
		// you can simply compare your output file (testcase_log/customers.txt) 
		// with the expected output file (testcase_log/customers_expected.text)
	}
	
	@Test
	public void test3StoreEWallets() {
		
		List<EWallet> wallets = new ArrayList<EWallet>();
		wallets.add(new EWallet(2, "BBB", "B123", 2000.00));
		wallets.add(new EWallet(3, "CCC", "C123", 20000.00));
		wallets.add(new EWallet(5, "EEE", "E123", 50000.00));
		
		assertEquals("2,BBB,2014896,2000.00", wallets.get(0).log());
		assertEquals("3,CCC,2044687,20000.00", wallets.get(1).log());
		assertEquals("5,EEE,2104269,50000.00", wallets.get(2).log());
		
		DataManagement.storeEWallets(this.WALLET_FILE, false, wallets);
		// If you cannot implement task 2 and task 4,
		// you can also compare your output file (testcase_log/wallets.txt) 
		// with the expected output file (testcase_log/wallets_expected.text)
	}
	
	@Test
	public void test4StoreOrders() {
		
		DataManagement.initStock(STOCK_FILE);
		DataManagement.initCustomer(CUSTOMER_FILE);
		DataManagement.initWallet(WALLET_FILE);
		
		System.out.println("----" + DataManagement.stockData.size());
		System.out.println("----" + DataManagement.customerData.size());
		System.out.println(DataManagement.customerData.get(2));
		
		Order a = new Order();
		assertTrue(a.setCustomerID(1));
		assertTrue(a.addItem("butter"));
		assertTrue(a.addItem("cocoa"));
		assertEquals(Order.Status.PAID, a.makePayment(new PaymentCash(a.calGrandTotal(), 1000)));
		assertEquals("1,1,butter|cocoa,PAID::CASH", a.log());
		
		Order b = new Order();
		assertTrue(b.setCustomerID(2));
		assertTrue(b.addItem("cereals"));
		assertTrue(b.addItem("cocoa"));
		assertEquals(Order.Status.PAID, b.makePayment(new PaymentCash(b.calGrandTotal(), 1000)));
		assertEquals("2,2,cereals|cocoa,PAID::CASH", b.log());
		
		Order c = new Order(5);
		assertTrue(c.setCustomerID(2));		// customer's id = 2
		assertTrue(c.addItem("cereals"));
		assertTrue(c.addItem("cocoa"));
		EWallet w = DataManagement.walletData.get(2);	// get wallet of customer's id 2
		assertEquals(Order.Status.PAID, c.makePayment(new PaymentEWallet(c.calGrandTotal(), w, "BBB", "B123")));
		assertEquals("5,2,cereals|cocoa,PAID::EWALLET", c.log());
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(a);
		orders.add(b);
		orders.add(c);
		
		DataManagement.storeOrders(this.ORDER_FILE, false, orders);
		// If you cannot implement task 2 and task 4,
		// you can also compare your output file (testcase_log/orders.txt) 
		// with the expected output file (testcase_log/orders_expected.text)
	}
}
