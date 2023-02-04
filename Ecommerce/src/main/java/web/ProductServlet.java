 /**
   Here servlet acts as a web page controller for the application, handling all
   requests from the user or frontend or website
 */

// jsp-api and servlet-api jar files used.
package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
// from servlet api jar file
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import dao.ProductDAO;


 

@WebServlet("/")
public class ProductServlet extends HttpServlet {  // To create a servlet the class must extend the HttpServlet class and override at least one of its methods
 	private ProductDAO ProductDaoVar;
	
	public void init() {
		ProductDaoVar = new ProductDAO();
	}

	//  doPost allow a servlet to handle a POST request
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
    // doGet Called by the server (via the service method) to allow a servlet to handle a GET request
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 
       /* getServletPath Returns the part of this request's URL that calls the servlet. This path starts with a "/" character and includes either the 
		servlet name or a path to the servlet, but does not include any extra path information or a query string */
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/delete":
				deleteProduct(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			default:
				listProducts(request, response);
				break;
			}
		} catch (SQLException ex) {
			/* ServletException  Constructs a new servlet exception when the servlet needs 
			   to throw an exception and include a message about the "root cause" exception 
			   that interfered with its normal operation, including a description message.
			 */

			throw new ServletException(ex);
		}
	}

	 
	
	/* The HttpServletRequest provides methods for accessing parameters of a request. 
	   The type of the request determines where the parameters come from. In most implementations, a GET request takes the parameters from the query string, while a POST request 
	   takes the parameters from the posted arguments.
	 */
	
    /* HttpServletResponse is a predefined interface present in javax. servlet. http package. 
       It can be said that it is a mirror image of request object.
       The response object is where the servlet can write information about the data it will send back.
     */
	// For listing all users
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = ProductDaoVar.selectAllProducts();
		request.setAttribute("listUser", listProduct);
		/* getRequestDispatcher Defines an object that receives requests from the client and sends them to any 
		   	resource (such as a servlet, HTML file, or JSP file) on the server.
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("productsList.jsp");
		dispatcher.forward(request, response);
	}

	// For showing new form
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("productsForm.jsp");
		dispatcher.forward(request, response);
	}

	// For displaying edit option
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = ProductDaoVar.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("productsForm.jsp");
		request.setAttribute("product", existingProduct); // Element.setAttribute() Sets the value of an attribute on the specified element.
		dispatcher.forward(request, response); // The forward() method works at server side. The sendRedirect() method works at client side.

	}

	// For inserting product and its details
	private void insertProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Product newUser = new Product(name, description, price, quantity);
		ProductDaoVar.insertProduct(newUser);
		response.sendRedirect("list");  // sendRedirect() method redirects the response to another resource, inside or outside the server
	}

	// For updating product and its details
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Product ProductsContainer = new Product(id, name, description, price, quantity);
		ProductDaoVar.updateProduct(ProductsContainer);
		response.sendRedirect("list");
	}

	// For deleting the products
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDaoVar.deleteProduct(id);
		response.sendRedirect("list");

	}

}