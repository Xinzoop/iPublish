/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublish.CoreConfService;

import com.iPublish.Entities.Coreconf;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zipv5_000
 */
@Stateless
@Path("com.ipublish.entities.coreconf")
public class CoreconfFacadeREST extends AbstractFacade<Coreconf> {
    @PersistenceContext(unitName = "iPublishPU")
    private EntityManager em;

    public CoreconfFacadeREST() {
        super(Coreconf.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Coreconf entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Coreconf entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Coreconf find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("search/{rank}/{query}")
    @Produces({"application/xml", "application/json"})
    public List<Coreconf> search(@PathParam("rank") String rank, @PathParam("query") String query) throws UnsupportedEncodingException{
        return super.search(rank, query);
    }
    
    @GET
    @Path("rank")
    @Produces(MediaType.TEXT_PLAIN)
    public String findRank(){
        return super.findRank();
    }
    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Coreconf> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Coreconf> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
