/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.models;

import com.demo.utils.Resource;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karim.omran
 */
@XmlRootElement(name = "type2")
public class Type2 implements XML {

    private Integer id;
    private String name;
    private List<EmpeddedElement> empeddedElements;
    
    @Override
    public Object getXMLType() {
        return Resource.getInstance().getProperty("com.demo.models.type2");
    }

    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElements(value = @XmlElement(name = "empedded_elements"))
    public List<EmpeddedElement> getEmpeddedElements() {
        return empeddedElements;
    }

    public void setEmpeddedElements(List<EmpeddedElement> empeddedElements) {
        this.empeddedElements = empeddedElements;
    }
    
    
    
}
