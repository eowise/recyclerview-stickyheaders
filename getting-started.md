---
layout: page
published: true
---

# Usage

Recyclerview-stickyheaders add sticky headers feature to a `RecyclerView` with a specific `ItemDecoration`. You will also need to create a layout for the header (or create views by code) and implement the `StickyHeadersAdapter` interface.

<div class="bs-callout bs-callout-warning">
<h4>Warning</h4>
Sticky headers will not work if <code>RecyclerView</code> adapter don't have stables ids!
</div>

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

<div class="bs-callout bs-callout-info">
You can find in the samples provided with the recyclerview-stickyheaders library a <a href="https://github.com/eowise/recyclerview-stickyheaders/blob/master/samples/src/main/java/com/eowise/recyclerview/stickyheaders/samples/adapters/InitialHeaderAdapter.java">basic implementation of <code>StickyHeadersAdapter</code></a>.
</div>


## Adding `StickyHeadersItemDecoration` to a `RecyclerView`

Sticky headers are drawed on top of RecyclerView items by a `com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration` object that you have to add to your RecyclerView :

{% highlight java %}
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  RecyclerView list = (RecyclerView)findViewById(R.id.list);
  PersonAdapter personAdapter = new PersonAdapter();

  list.setAdapter(personAdapter);
  list.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

  // Build item decoration and add it to the RecyclerView
  StickyHeadersItemDecoration decoration = new StickyHeadersBuilder()
      .setAdapter(personAdapter)
      .setRecyclerView(list)
      .setStickyHeadersAdapter(
          new InitialHeaderAdapter(), // Class witch implements StickyHeadersAdapter
          HeaderPosition.OVERLAY)     // Decoration position relative to a item
      .build();

  list.addItemDecoration(list);
}
{% endhighlight %}

And you're done!
