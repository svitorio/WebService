/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.MensagemDAO;
import dao.UsuarioDAO;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Mensagem;
import modelo.Usuario;

/**
 * REST Web Service
 *
 * @author desenvolverdor
 */
@Path("Fazenda")
public class FazendaWs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FazendaWs
     */
    public FazendaWs() {
    }

    /**
     * Retrieves representation of an instance of ws.FazendaWs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
       // throw new UnsupportedOperationException();
       return "Meu primeiro Hello World na Web Service";
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/Calculo/{valor1}/{operacao}/{valor2}")
    public String getCalculo(@PathParam("valor1") float valor1,
                            @PathParam("valor2")float valor2,
                            @PathParam("operacao")String oper) {
     
       String result;
       float v;
       System.out.println("Valor 1: "+valor1+"\nValor 2: "+valor2+"\nOperação: "+oper);
       if(oper.equals("+")){
           v=valor1+valor2;
           result= ""+v;
       }
       else if(oper.equals("-")){
           v=valor1-valor2;
           result= ""+v;
       }
       else if(oper.equals("div")){
           v=valor1/valor2;
           result= ""+v;
       }
       else{
           v=valor1*valor2;
           result= ""+v;
       }
        //Converter para Json
       
        return result;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/test/apagar/{telefone}")
    public String getApagar(@PathParam("telefone") int telefone) {
       Usuario u = new Usuario();

       u.setFone(telefone);
       
       UsuarioDAO dao= new UsuarioDAO();
       dao.excluir(u);
       
        //Converter para Json
       
       Gson g = new Gson();
       return g.toJson(u);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/inserir/{nome}/{email}/{telefone}")
    public String getInserir(@PathParam("nome") String nome,
                            @PathParam("email") String email,
                            @PathParam("telefone") int telefone){
        //Gson g=new Gson()
        UsuarioDAO dao =new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setFone(telefone);
        boolean a = dao.inserir(usuario);
        String resultado;
        if(a==false)
            resultado = "Ta bem não Viado";
        else
            resultado = "Ai dentu";
        
        return resultado;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/test2/{numero}")
    public String getCalculo(@PathParam("numero") String a) {
       System.out.println("Chegou aqui: "+a);
       int[] numero=new int[a.length()];
       String[] pala =a.split("");
       for(int i=0;i<a.length();i++){
           numero[i]= Integer.parseInt(pala[i]);
       }
       int aux=0;
       for(int i=0;i<numero.length;i++){
           for(int j=0;j<numero.length;j++){
               if(numero[i]<numero[j]){
                   aux=numero[i];   
                   numero[i]= numero[j];
                   numero[j]=aux;
               }
           }
       }
       Gson g = new Gson();
       return g.toJson(numero);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/list")
    public String listUsuario() {
        
       List<Usuario> usu = null;       
       UsuarioDAO dao = new UsuarioDAO();
       
       usu = dao.listar();
   
       //Converter para Json
       Gson g = new Gson();
       return g.toJson(usu);
    }
    /**
     * PUT method for updating or creating an instance of FazendaWs
     * @param content representation for the resource
     */
  
    /**
     * PUT method for updating or creating an instance of FazendaWs
     * @param nome
     * @param mensagem
     * @param tempo
     * @param content representation for the resource
     * @return 
     */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("Usario/inserirwork/{nome}/{mensagem}/{tempo}")
    public void getInserirwork(@PathParam("nome") String nome,
                                @PathParam("mensagem") String mensagem,
                                @PathParam("tempo") String tempo){
       
        /* GregorianCalendar calendar = new GregorianCalendar();
        double hora = calendar.HOUR_OF_DAY;
        System.out.println(hora);
        double tempochegada = System.currentTimeMillis();
        */
                //String data = "dd/MM/yyyy";
		String hora = "h:mm:ss:SSS";
		String hora1;
                /*data1*/
		Date agora = new Date();
		SimpleDateFormat formata; //= new SimpleDateFormat(data);
		//data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
		//System.out.print(data1+"");
		System.out.print("Tempo Saida:"+tempo+"\nChegada:"+hora1);
        MensagemDAO dao2 =new MensagemDAO();
        Mensagem msg = new Mensagem();
        msg.setMsg(mensagem);
        msg.setTempo(tempo);
        msg.setTempochegada(hora1);
        msg.setNome(nome);
        boolean g = dao2.inserir(msg);
            //if(!g)
          //      return "false";
            //  else
            //    return "true";
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    public void putJson(String content) {
    }
}
