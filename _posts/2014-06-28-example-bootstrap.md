---
title: "Shiori by Example: Helpful Bootstrap Components"
---

With Shiori, you can take advantage of all the available components in Bootstrap. Here are some useful components for blogging.

## Buttons and Font Awesome

Shiori automatically imports the latest version of [Font Awesome](http://fortawesome.github.io/Font-Awesome). You can use it with `button`s like so:

<button type="button" class="btn btn-primary"><i class="fa fa-twitter"></i> Tweet</button>
<button type="button" class="btn btn-default"><i class="fa fa-envelope-o"></i> Email</button>

## Text Helpers

You can use text helper classes to modify text easily. Documentations [here](http://getbootstrap.com/css/#helper-classes-colors) and [here](http://getbootstrap.com/css/#type-alignment).

<p class="text-muted">Use <code>text-muted</code> to mute text color.</p>

<p class="lead">Use <code>lead</code> to for call-out text.</p>

<p class="text-center">Use <code>text-center</code> to center text.</p>

## Alerts

<div class="alert alert-success" role="alert">Alerts can be useful too!</div>

## Responsive Embed

Add `embed-responsive` and either `embed-responsive-16by9` or `embed-responsive-4by3` classes to make embedded objects responsive.

<div class="embed-responsive embed-responsive-16by9">
<iframe width="1280" height="720" src="//www.youtube.com/embed/J_vGbXDAvmQ?rel=0" frameborder="0" allowfullscreen></iframe>
</div>

## JavaScript Components

You can use all of the JavaScript components included by Bootstrap. Example: <a data-toggle="modal" data-target="#myModal" href="#">A modal dialog.</a>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Modal Dialog</h4>
      </div>
      <div class="modal-body">
        <p>Hello World!</p>
      </div>
    </div>
  </div>
</div>

## Customizing Bootstrap

Shiori uses [bootstrap-sass](https://github.com/twbs/bootstrap-sass), so all of the variables are customizable. To learn more, check out Shiori's [README](http://github.com/ellekasai/shiori/).
