package edu.bgsu.notebook;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

@Named
@SessionScoped
public class NotebookManagerBean implements Serializable
{
	private static final long serialVersionUID = 4105775284798910753L;
	
	// The name of the application.  Declared here so it can be "plugged in" in other places throughout the GUI.
	final static String applicationName = "Research Notebook Manager";

	// The body text of the currently-loaded note.
	private String noteText = "";
	
	private String autocompleteText = "word";

	private String keyboardColor = NotebookColors.GREEN.getHexString();

	/**
	 * Default constructor.
	 */
	public NotebookManagerBean()
	{
		// Important:  Put initialization stuff into the init() method.  DO NOT put initializations here.
	}
	
	/**
	 * Initialization method.
	 */
	@PostConstruct
	void init()
	{
		
	}
	
	/**
	 * File upload listener for the 'open notebook' welcome option.  Opens and parses the supplied notebook
	 * file, and redirects to the main application page.
	 * @param e The file upload event
	 */
	public void openNotebook( FileUploadEvent e )
	{
		try 
		{
			// TODO Read in the notebook file.
			
			
			// Redirect to the main application page.
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} 
		catch (IOException e1) { e1.printStackTrace(); }
	}
	
	/**
	 * Simple utility function to add a message to the FacesContext.
	 * @param summary The message summary.
	 * @param detail The message detail.
	 * @param severity The message severity.
	 */
	private static void message(String summary, String detail, FacesMessage.Severity severity)
	{
		FacesMessage message = new FacesMessage();
		message.setSeverity(severity);
		message.setSummary(summary);
		message.setDetail(detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public List<Note> getDemoNotes()
	{
		List<Category> categories = Arrays.asList( new Category[]{ new Category("Demo Notes", "These are for demo/testing purposes only!") } );
		List<Note> notes = new ArrayList<Note>();
		for( int i = 0; i < 10; i++ )
			notes.add( new Note("Note " + i + " Title", 
								"Note " + i + " Comments",
								NotebookColors.BLUE,
								categories) );
		return notes;
	}
	
	public void autocompleteValueChanged(ValueChangeEvent e)
	{
		System.out.println("Autocomplete text changed: " + (String)e.getNewValue());
		// TODO
	}
	
	// ---------- Generic getters and setters below here ----------
	
	public String getKeyboardColor() 
	{
		return keyboardColor;
	}
	
	public String getAutocompleteText() 
	{
		return autocompleteText;
	}

	public void setAutocompleteText(String autocompleteText) 
	{
		System.out.println("Changing autocomplete text: " + autocompleteText);
		this.autocompleteText = autocompleteText;
	}
	
	/**
	 * Returns the name of the application.
	 * @return The application name.
	 */
	public String getApplicationName()
	{
		return applicationName;
	}
	
	/**
	 * Gets the body text of the current note.
	 * @return The current note body text.
	 */
	public String getNoteText() 
	{
		return noteText;
	}

	/**
	 * Sets the body text of the current note.
	 * @param noteText
	 */
	public void setNoteText(String noteText) 
	{
		this.noteText = noteText;
	}
}