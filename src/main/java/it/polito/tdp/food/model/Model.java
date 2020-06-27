package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	private List<Food> cibi;
	private FoodDao dao;
	private Graph<Food, DefaultWeightedEdge> grafo;
	
	public Model() {
	this.dao=new FoodDao();	
	}
	
	
	public List<Food> getCibi(int porzioni){
		
		this.cibi=dao.getCiboByPorzioni(porzioni);
		
		this.grafo=new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, cibi);
		
		for(Food f1 : grafo.vertexSet()){
			for(Food f2 : grafo.vertexSet()) {
				if(!f1.equals(f2) && f1.getFood_code()<f2.getFood_code()) {
					Double peso = dao.getPesoArco(f1, f2);
					if(peso!=null) {
						Graphs.addEdgeWithVertices(grafo, f1, f2, peso);
					}
				}
			}
		}
		return this.cibi;
		
	}
	
	public List<Adiacenza> elencoCibiConnessi(Food f){
		
		List<Adiacenza> lista = new ArrayList<>();
		
		List<Food> vicini = Graphs.neighborListOf(grafo, f);
		
		for(Food ff : vicini) {
			Double peso = grafo.getEdgeWeight(grafo.getEdge(f, ff));
			lista.add(new Adiacenza(ff, peso));
		}
		
		Collections.sort(lista);
		
		return lista;
	}
	
	
}
