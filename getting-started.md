---
layout: page
---

# Install

## Using Gradle

Add the following to your build.gradle:

{% highlight groovy %}
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.eowise:recyclerview-stickyheaders:0.1.0@aar'
}
{% endhighlight %}

## Using Maven

Add the following to your pom.xml:

{% highlight xml %}
<dependency>
    <groupId>com.eowise</groupId>
    <artifactId>recyclerview-stickyheaders</artifactId>
    <version>0.1.0</version>
</dependency>
{% endhighlight %}

# Usage

Recyclerview-stickyheaders add sticky headers feature to a `RecyclerView` with a specific `ItemDecoration`. You will also need to create a layout for the header and implement the `StickyHeadersAdapter` interface.

## `StickyHeadersAdapter` interface

This interface is used to create and bind the headers view holders, and to specify on witch header each item belongs.

{% highlight java %}
public interface StickyHeadersAdapter<
    ItemViewHolder extends RecyclerView.ViewHolder,
    HeaderViewHolder extends RecyclerView.ViewHolder
  > {
  /**
   *
   */
  HeaderViewHolder onCreateViewHolder(ViewGroup parent);
  void onBindViewHolder(HeaderViewHolder headerViewHolder, ItemViewHolder itemViewHolder, int position);
  long getHeaderId(ItemViewHolder viewHolder, int position);
}
{% endhighlight %}