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
package org.chrolab.entity.mixin;

import org.chrolab.constant.LabNodeTypes;
import org.chromattic.api.annotations.MixinType;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.Property;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 23, 2012  
 */
@MixinType(name = LabNodeTypes.RENAMED_MIXIN)
public abstract class RenamedMixin {
  
  @Property(name = LabNodeTypes.Property.OLD_NAMES)
  public abstract void setOldNames(String[] ids);
  public abstract String[] getOldNames();
}
