/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karim.omran
 */
@XmlRootElement(name = "empedded_element")
public class EmpeddedElement {
    
    private String empeddedProperty;

    @XmlElement(name="empedded_property")
    public String getEmpeddedProperty() {
        return empeddedProperty;
    }

    public void setEmpeddedProperty(String empeddedProperty) {
        this.empeddedProperty = empeddedProperty;
    }
    
}
