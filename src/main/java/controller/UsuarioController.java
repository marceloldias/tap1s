package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

@WebServlet(urlPatterns = "/usucontroller")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<Usuario> lista = new ArrayList<>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// capturando o que vem do client
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");

		Usuario usu = new Usuario(nome, email);

		// inserir na lista
		lista.add(usu);

		resp.getWriter().print("\n{Nome: " + usu.getNome() + " email: " + usu.getEmail() + " }");
		// Respondendo ao client
		// resp.getWriter().print("\nOla "+ nome+" email: "+ email);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int ind = Integer.parseInt(req.getParameter("ind"));
		
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");	
		
				if(nome != null){
					lista.get(ind).setNome(nome);
				}
				if(email != null){
					lista.get(ind).setEmail(email);
				}
				
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ind = Integer.parseInt(req.getParameter("ind"));	
			
				lista.remove(ind);
			

	}

	//// @Override
	//// protected void doDelete(HttpServletRequest req, HttpServletResponse
	//// resp) throws ServletException, IOException {
	////
	//// String nome = req.getParameter("nome");
	////
	//// for(int i = 0; i<lista.size();i++){
	//// if(lista.get(i).getNome().equals(nome)){
	//// lista.remove(i);
	//// i=-1;
	//// }
	//// }
	//
	// }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String json = "[";
		for (int i = 0; i < lista.size(); i++) {
			json += "\n{Nome: " + lista.get(i).getNome() + " ,email: " + lista.get(i).getEmail() + " }";

			if (i < lista.size() - 1)
				json += ",";

		}
		json += "\n]";

		resp.getWriter().print("" + json);
	}

}
