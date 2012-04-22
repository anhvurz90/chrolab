package org.chrolab.entity;

import org.chromattic.api.RelationshipType;
import org.chromattic.api.annotations.ManyToOne;
import org.chromattic.api.annotations.MappedBy;
import org.chromattic.api.annotations.Name;
import org.chromattic.api.annotations.PrimaryType;
import org.chrolab.constant.LabNodeTypes;

@PrimaryType(name=LabNodeTypes.TAG_LINK)
public abstract class TagLink {

  /**
   * Name of tag link
   * @return name
   */
  @Name
  public abstract String getName();

  /**
   * Set name
   * @param name
   */
  public abstract void setName(String name);

  /**
   * Set referenced book
   * @param book
   */
  @ManyToOne(type = RelationshipType.REFERENCE)
  @MappedBy(LabNodeTypes.Property.BOOK_REF)
  public abstract void setBook(Book book);

  /**
   * Get referenced book
   * @return a book
   */
  public abstract Book getBook();

  /**
   * Set referenced tag
   * @param tag
   */
  @ManyToOne(type = RelationshipType.REFERENCE)
  @MappedBy(LabNodeTypes.Property.TAG_REF)
  public abstract void setTag̣(Tag tag);

  /**
   * Get referenced tag
   * @return
   */
  public abstract Tag getTag̣();

}
