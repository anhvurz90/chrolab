package org.exoplatform.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.chromattic.api.RelationshipType;
import org.chromattic.api.annotations.Destroy;
import org.chromattic.api.annotations.ManyToOne;
import org.chromattic.api.annotations.MappedBy;
import org.chromattic.api.annotations.Name;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.Property;
import org.exoplatform.constant.LabNodeTypes;
import org.exoplatform.services.jcr.util.IdGenerator;

@PrimaryType(name= LabNodeTypes.TAG)
public abstract class Tag {
  
  /**
   * Get name of tag
   * @return tag name
   */
  @Name
  public abstract String getName();

  /**
   * Set name to tag
   * @param name
   */
  public abstract void setName(String name);
  
  /**
   * Destroy
   */
  @Destroy
  public abstract void remove();
  
  /**
   * Get book title
   * @return title
   */
  @Property(name = LabNodeTypes.Property.TITLE)
  public abstract String getTitle();
  
  /**
   * Set a title  
   * @param title
   */
  public abstract void setTitle(String title);
  
  /**
   * Get tag link of this tag
   * @return tag link
   */
  @OneToMany(type = RelationshipType.REFERENCE)
  @MappedBy(LabNodeTypes.Property.TAG_REF)
  public abstract Collection<TagLink> getTagLinks();
  
  /**
   * Get a book by a given name
   * @param name
   * @return book
   */
  public Book getBook(String name) {
    return getBooks().get(name);
  };

  /**
   * Get total books
   * @return a map of books
   */
  public Map<String, Book> getBooks() {
    Iterator<TagLink> iter = getTagLinks().iterator();
    Map<String, Book> result = new HashMap<String, Book>();
    while (iter.hasNext()) {
      TagLink tagLink = iter.next();
      Book book = tagLink.getBook();
      result.put(book.getName(), book);
    }
    return result;
  }
  
  /**
   * Add this tag to a book
   * @param book
   */
  public void addBook(Book book) {
    TagLink tagLink = getTagStore().createTagLink();
    tagLink.setName(IdGenerator.generate());
    getTagStore().addTagLink(tagLink);
    tagLink.setTag̣(this);
    tagLink.setBook(book);
  }
  
  /**
   * Get tag storage
   * @return tag store
   */
  @ManyToOne
  public abstract TagStore getTagStore();
}
