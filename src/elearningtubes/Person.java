/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearningtubes;

/**
 *
 * @author AGUTUGA
 */
public abstract class Person {
    private String name;
    private String id;
    
    
    public Person(String nm, String id) {
        setName(nm);
        setId(id);
    }
    
    public Person(String nm) {
        setName(nm);
        
    }
    
    public void setName(String name) {
        if (!" ".equals(name)) {
            this.name = name;
        }
    }
    
    public void setId(String id) {
        if (!" ".equals(id)) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}