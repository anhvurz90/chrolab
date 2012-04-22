package org.exoplatform.entity;

import java.util.Map;

import org.chromattic.api.annotations.Create;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.PrimaryType;
import org.exoplatform.constant.LabNodeTypes;


@PrimaryType(name= LabNodeTypes.TAG_STORE)
public abstract class TagStore {

  /**
   * Get tag collection 
   * @return map of tags
   */
  @OneToMany
  public abstract Map<String, Tag> getTags();
  
  /**
   * Set a map of tags
   * @param tags
   */
  public abstract void setTags(Map<String, Tag> tags);
  
  /**
   * Create a tag
   * @return tag
   */
  @Create
  public abstract Tag createTag();
  
  /**
   * Get a tag with a given name
   * @param name
   * @return tag
   */
  public Tag getTag(String name) {
    return getTags().get(name);
  }
  
  /**
   * Add a tag
   * @param tag
   */
  public void addTag(Tag tag) {
    getTags().put(tag.getName(), tag);
  }
  
  /**
   * Get total tag links
   * @return a map of tag links
   */
  @OneToMany
  public abstract Map<String, TagLink> getTagLinks();
  
  /**
   * Create a tag link
   * @return a tag link
   */
  @Create
  public abstract TagLink createTagLink();
  
  /**
   * Add a taglink
   * @param tagLink
   */
  public void addTagLink(TagLink tagLink) {
    getTagLinks().put(tagLink.getName(), tagLink);
  }
}
