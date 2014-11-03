package org.meg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meg.dao.QuadroDAO;
import org.meg.model.Descricao;
import org.meg.model.Estado;
import org.meg.model.Quadro;
import org.meg.model.Secao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/grafico")
public class GraficoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraficoServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/**
	 * Metodo que realiza o busca de dados para plotar grafico
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Quadro> quadros = new ArrayList<>();
		int idDescricao = 0;
		int idSetor = 0;
		int idEstado = 0;
		int anoInicial = 0;
		int anoFinal = 0;
		idDescricao = Integer.valueOf(request.getParameter("descricao"));
		idSetor = Integer.valueOf(request.getParameter("setor"));
		idEstado= Integer.valueOf(request.getParameter("estado"));
		anoInicial = Integer.valueOf(request.getParameter("anoInicial"));
		anoFinal = Integer.valueOf(request.getParameter("anoFinal"));
		QuadroDAO dao = new QuadroDAO();
		Descricao descricao = new Descricao(idDescricao);
		Secao secao = new Secao(idSetor);
		Estado estado = new Estado (idEstado);
		quadros = dao.obterLista(anoInicial, anoFinal, estado, secao, descricao);
		request.getSession().setAttribute("valores", listarValores(quadros));
		request.getSession().setAttribute("anos", listarAnos(quadros));
		request.getSession().setAttribute("tamanho", quadros.size());
		request.getSession().setAttribute("titulo", descricao.getNome());
		request.getSession().setAttribute("secao", secao.getNome());
		request.getSession().setAttribute("estado", estado.getNome());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("grafico.jsp");
		requestDispatcher.forward(request, response);
	}
	/**
	 * Lista os valores dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return	uma lista de floats contendo os valores
	 */
	private List<Float> listarValores(List<Quadro> quadros){
		List<Float> valores = new ArrayList<Float>();
		for( Quadro q: quadros){
			valores.add(q.getValor());
		}
		return valores;
	}
	
	/**
	 * Lista os anos dos Quadros contidos na lista global 'quadros'
	 * 
	 * @return uma lista de Strings contendo os anos
	 */
	private List<String> listarAnos(List<Quadro> quadros){
		List<String> anos = new ArrayList<String>();
		for( Quadro q: quadros){
			anos.add(String.valueOf(q.getAno()));
		}
		return anos;
	}
}