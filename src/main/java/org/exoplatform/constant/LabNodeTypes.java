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
package org.exoplatform.constant;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 16, 2012  
 */
public interface LabNodeTypes {
  public final static String BOOK_STORE = "lab:bookstore";

  public final static String BOOK       = "lab:book";

  public interface Property {
    public final static String AUTHOR       = "author";

    public final static String CREATED_DATE = "createdDate";

    public final static String UPDATED_DATE = "updatedDate";

    public final static String TITLE        = "title";

    public final static String CODE         = "code";

  }

}
