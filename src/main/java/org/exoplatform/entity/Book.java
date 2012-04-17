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
package org.exoplatform.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;

import org.chromattic.api.annotations.DefaultValue;
import org.chromattic.api.annotations.Destroy;
import org.chromattic.api.annotations.Name;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.Property;
import org.exoplatform.constant.LabNodeTypes;

/**
 * Created by The eXo Platform SASWikiNodeType
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 16, 2012  
 */
@PrimaryType(name = LabNodeTypes.BOOK)
public abstract class Book {  
  
  @Name
  public abstract String getId();
  
  public abstract void setId(String id);

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
  
  
  @ManyToOne
  public abstract BookStore getBookStore();
  
  @Destroy
  public abstract void remove();

}
