package com.github.mwiede.crud.demo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import com.github.mwiede.crud.demo.services.CustomerService;
import com.github.mwiede.crud.demo.services.NoteService;
import com.github.mwiede.crud.demo.model.Customer;
import com.github.mwiede.crud.demo.model.Note;

@ManagedBean(name = "dtFilterView")
@ViewScoped
public class CustomerViewBean implements Serializable {

    @Inject
    private CustomerService service;

    @Inject
    private NoteService noteService;

    private List<Customer> customers;

    private List<Customer> filteredCustomers;

    private Customer currentCustomer;

    private String currentNote;

    private Note currentNoteEntity;

    private List<Note> currentNotes;

    @PostConstruct
    public void init() {
        customers = service.getCustomers();
    }

    public void onRowSelect(SelectEvent event) {
        currentCustomer = (Customer) event.getObject();
        currentNotes = new ArrayList(currentCustomer.getNotes());
    }

    // because jsf does not support sorting a java.util.Set
    public List<Note> getCurrentCustomersNotes() {
        return currentNotes;
    }

    public void selectNote(Note event) {
        currentNoteEntity = event;
    }

    public void saveNewNoteButtonAction() {
        Note aNew = noteService.createNew(currentCustomer, currentNote);
        currentCustomer.getNotes().add(aNew);
        currentNotes = new ArrayList(currentCustomer.getNotes());
        this.currentNote = "";
    }

    public void updateNoteButtonAction() {
        currentNoteEntity = noteService.update(currentNoteEntity);
        int indexOf = currentNotes.indexOf(currentNoteEntity);
        currentNotes.set(indexOf, currentNoteEntity);
        currentNoteEntity = null;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> customers) {
        this.filteredCustomers = customers;
    }

    public Customer.CustomerStatus[] getStatuses() {
        return Customer.CustomerStatus.values();
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void deleteNoteById(Long id) {
        currentCustomer.getNotes().removeIf(note -> note.getId().equals(id));
        currentNotes = new ArrayList(currentCustomer.getNotes());
        noteService.delete(id);
    }

    public void onStatusEdit(CellEditEvent event) {
        Customer updatedCustomer = service.update(customers.get(event.getRowIndex()));
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Status Changed",
                    "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public String getCurrentNote() {
        return currentNote;
    }

    public void setCurrentNote(String currentNote) {
        this.currentNote = currentNote;
    }

    public Note getCurrentNoteEntity() {
        return currentNoteEntity;
    }
}
