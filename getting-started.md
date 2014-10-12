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
    <version>0.2.0</version>
</dependency>
{% endhighlight %}

# Usage

Recyclerview-stickyheaders add sticky headers feature to a `RecyclerView` with a specific `ItemDecoration`. You will also need to create a layout for the header (or create views by code) and implement the `StickyHeadersAdapter` interface.

## `StickyHeadersAdapter` interface

This interface is used to create and bind the headers view holders, and to specify on witch header each item belongs.

{% highlight java %}
public interface StickyHeadersAdapter<HeaderViewHolder extends ViewHolder>
{% endhighlight %}

The parametrized type `HeaderViewHolder` is the ViewHolder for headers.

`StickyHeadersAdapter` interface consists of 3 methods :

{% highlight java %}
// Create and returns header ViewHolder witch encapsulate the header view.
HeaderViewHolder onCreateViewHolder(ViewGroup parent);

// Binds headerViewHolder underlying views using its position.
void onBindViewHolder(HeaderViewHolder headerViewHolder, int position);

// Returns the header id matching the item postion
long getHeaderId(int position);
{% endhighlight %}

You can find in the samples provided with the recyclerview-stickyheaders library a [basic implementation of `StickyHeadersAdapter`](https://github.com/eowise/recyclerview-stickyheaders/blob/master/samples/src/main/java/com/eowise/recyclerview/stickyheaders/samples/adapters/InitialHeaderAdapter.java).

## Adding `StickyHeadersItemDecoration` to a `RecyclerView`

Sticky headers are drawed on top of RecyclerView items by a `com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration` object that you have to add to your RecyclerView :

{% highlight java %}
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  RecyclerView list = (RecyclerView)findViewById(R.id.list);
  PersonAdapter personAdapter = new PersonAdapter();

  // Create the item decoration witch will draw sticky headers
  StickyHeadersItemDecoration letterHeaderDecoration = new StickyHeadersItemDecoration(
    new InitialHeaderAdapter(), // Class witch implements StickyHeadersAdapter
    list,                       // The RecyclerView
    HeaderPosition.OVERLAY      // Decoration position relative to a item
  );

  list.setAdapter(personAdapter);
  list.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
  list.addItemDecoration(letterHeaderDecoration); // Add item decoration to the RecyclerView
}
{% endhighlight %}

And you're done!

If you plan to implements adding and/or removing items features, you must add the code below in order to enable item decoration drawing while items animations are running.

{% highlight java %}
personAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
  @Override
  public void onItemRangeInserted(int positionStart, int itemCount) {
    ViewCompat.postOnAnimationDelayed(list, new InvalidateAnimationRunnable(list), 50);
  }

  @Override
  public void onItemRangeRemoved(int positionStart, int itemCount) {
    ViewCompat.postOnAnimationDelayed(list, new InvalidateAnimationRunnable(list), 50);
  }
});
{% endhighlight %}