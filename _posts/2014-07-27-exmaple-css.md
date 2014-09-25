---
title: "Shiori by Example: Fundamental HTML Elements"
---

Shiori uses [Bootstrap](http://getbootstrap.com/) for styling. Here's how fundamental HTML elements are displayed on Shiori. To learn more, check out Bootstrap's [documentation](http://getbootstrap.com/css/).

## Headings

**Do not use `h1` tags**, which is reserved for the post title. Use `h2` tags mostly, and use `h3` if you need subheadings.

## Paragraphs

**This is a regular paragraph.** Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum.

Links look like [this](#). *Emphasis*. <mark>Marked</mark>. <del>Deleted</del>. <code>Code</code>. <kbd>user-input</kbd>.

## Blockquotes

> **This is a blockquote**. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Sed posuere consectetur est at lobortis.

## Lists

* Unordered List
* Unordered List
* Unordered List

1. Ordered List
1. Ordered List
1. Ordered List

## Tables

In Shiori, you don't need to add the Bootstrap `.table` class to `table`. It just works.

<table>
  <thead>
    <tr>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Username</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Elle</td>
      <td>Kasai</td>
      <td><a href="http://twitter.com/ellekasai">@ellekasai</a></td>
    </tr>
  </tbody>
</table>

## Code

{% highlight ruby %}
def print_hi(name)
  puts "Hi, #{name}"
end
print_hi('Elle')
#=> prints 'Hi, Elle' to STDOUT.
{% endhighlight %}

## Images

Images have round corners and are responsive by default.

![](http://placehold.it/1200x150)

## That's it!

Continue onto [{{ page.previous.title }}]({{ page.previous.url | prepend:site.baseurl }}) to learn more.
