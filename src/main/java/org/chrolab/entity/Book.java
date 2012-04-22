/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.chrolab.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.chromattic.api.RelationshipType;
import org.chromattic.api.annotations.DefaultValue;
import org.chromattic.api.annotations.Destroy;
import org.chromattic.api.annotations.ManyToOne;
import org.chromattic.api.annotations.MappedBy;
import org.chromattic.api.annotations.Name;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.Property;
import org.chrolab.constant.LabNodeTypes;
import org.exoplatform.services.jcr.util.IdGenerator;

/**
 * Created by The eXo Platform SASWikiNodeType
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 16, 2012  
 */
@PrimaryType(name = LabNodeTypes.BOOK)
public abstract class Book {  
  
  /**
   * Name of book
   * @return name
   */
  @Name
  public abstract String getName();
  
  /**
   * name of book
   * @param name
   */
  public abstract void setName(String name);

  /**
   * @return the author
   */
  @Property(name = LabNodeTypes.Property.AUTHOR)
  @DefaultValue("anonymous")
  public abstract String getAuthor();

  /**
   * @param author the author to set
   */
  public abstract void setAuthor(String author);

  /**
   * @return the createdDate
   */
  @Property(name= LabNodeTypes.Property.CREATED_DATE)
  public abstract Date getCreatedDate();

  /**
   * @param createdDate the createdDate to set
   */
  public abstract void setCreatedDate(Date createdDate);

  /**
   * @return the updatedDate
   */
  @Property(name=LabNodeTypes.Property.UPDATED_DATE)
  public abstract Date getUpdatedDate();

  /**
   * @param updatedDate the updatedDate to set
   */
  public abstract void setUpdatedDate(Date updatedDate);

  /**
   * @return the title
   */
  @Property(name=LabNodeTypes.Property.TITLE)
  public abstract String getTitle();

  /**
   * @param title the title to set
   */
  public abstract void setTitle(String title);

  /**
   * @return the codes
   */
  @Property(name=LabNodeTypes.Property.CODE)
  public abstract List<String> getCodes();

  /**
   * @param codes the code to set
   */
  public abstract void setCodes(List<String> codes);
  
  /**
   * Get book store
   * @return book store
   */
  @ManyToOne
  public abstract BookStore getBookStore();
  
  /**
   * Get tag link of this book
   * @return tag link
   */
  @OneToMany(type = RelationshipType.REFERENCE)
  @MappedBy(LabNodeTypes.Property.BOOK_REF)
  public abstract Collection<TagLink> getTagLinks();
  
 /**
  * Get a tag by a given name
  * @param name of tag
  * @return a tag
  */
  public Tag getTag(String name) {
    return getTags().get(name);
  };

  /**
   * Get total tags
   * @return a map of tags
   */
  public Map<String, Tag> getTags() {
    Iterator<TagLink> iter = getTagLinks().iterator();
    Map<String, Tag> result = new HashMap<String, Tag>();
    while (iter.hasNext()) {
      TagLink tagLink = iter.next();
      Tag tag = tagLink.getTag̣();
      result.put(tag.getName(), tag);
    }
    return result;
  }
  
  /**
   * Add a tag to this book
   * @param tag
   */
  public void addTag(Tag tag) {
    TagLink tagLink = getBookStore().getTagStore().createTagLink();
    tagLink.setName(IdGenerator.generate());
    getBookStore().getTagStore().addTagLink(tagLink);
    tagLink.setBook(this);
    tagLink.setTag̣(tag);
  }
  
  /**
   * Destroy
   */
  @Destroy
  public abstract void remove();

}
