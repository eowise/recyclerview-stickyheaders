# :ribbon: Hi! I'm Shiori, a Bootstrap-based Jekyll Theme. :ribbon:

![](https://cloud.githubusercontent.com/assets/992008/3955483/2b9a77ae-2702-11e4-9f28-6afb051271de.png)

## :ribbon: Live Demo :ribbon:

### [You can view the live demo of myself here](http://ellekasai.github.io/shiori).

## :ribbon: Screenshots :ribbon:

### Desktop/Tablet

![](http://cl.ly/image/3a2M1D3E3b3d/screenshot%202014-08-30%20at%206.51.34%20PM.png)

## :ribbon: Color Schemes :ribbon:

I wear different colors (based on [Flat UI Colors](http://flatuicolors.com/)):

### Dark

![](http://cl.ly/image/3d3T3b3E0b45/screenshot%202014-08-30%20at%206.48.56%20PM.png)

---

### Turquoise

![](http://cl.ly/image/1s1k3m1E3n37/screenshot%202014-08-30%20at%206.49.02%20PM.png)

---

### Green

![](http://cl.ly/image/0G0G3u392I11/screenshot%202014-08-30%20at%206.49.08%20PM.png)

---

### Blue

![](http://cl.ly/image/1V1C0n2U0c1s/screenshot%202014-08-30%20at%206.49.14%20PM.png)

---

### Purple

![](http://cl.ly/image/1V283M0f1K08/screenshot%202014-08-30%20at%206.49.19%20PM.png)

---

### Orange

![](http://cl.ly/image/2d2k010J3z3g/screenshot%202014-08-30%20at%206.49.24%20PM.png)

---

### Red

![](http://cl.ly/image/10080E2G3c1e/screenshot%202014-08-30%20at%206.49.31%20PM.png)

---

## :ribbon: Usage :ribbon:

**Note:** If you're not familiar with Jekyll, please read up on [Jekyll's documentation](http://jekyllrb.com/) first.

[Fork this repo](http://github.com/ellekasai/shiori/fork), clone it, and then run:

```
bundle install
```

...which installs `github-pages` gem. After that, run the server:

```
jekyll serve --watch
```

### :warning: Warning :warning:

Once the server is started, you must go to [http://localhost:4000/shiori/](http://localhost:4000/shiori/), since `baseurl` is set as `"/shiori"` initially.

To use  http://localhost:4000/, change `baseurl` in `_config.yml` to `""` .

### Keeping Shiori Up to Date

To keep Shiori up to date, follow these steps:

1. [Configure a remote for a fork](https://help.github.com/articles/configuring-a-remote-for-a-fork)
2. [Sync a fork](https://help.github.com/articles/syncing-a-fork) (**Note**: Use `gh-pages` instead of `master`.)

## :ribbon: Customization :ribbon:

### Required

You **should** change these files before deploying:

* `_config.yml`: You must change `baseurl`, `url`, and `title`. Others are optional.
  * Make sure to restart the server after you update `_config.yml`.
* `CNAME`: Change this to host Shiori on a custom domain.
* `README.md`: Write your own README!
* `_includes/nav.html`: Modify or remove navigation links.
* `_includes/nav-right.html`: Modify or remove navigation links.
* `_includes/sidebar.html`: Customize the side bar.
* `_includes/post-header.html`: Customize the post header.
* `_includes/post-footer.html`: Customize the post footer.
* `_includes/footer.html`: Add copyright info, etc.

### Optional

You **may** customize these files - they're optional:

* `favicon.ico`: Favicon.
* `about.html`: About page. Currently not linked from anywhere.
* `_includes/head.html`: Extra stuff in the `<head>` tag.
* `_includes/scripts.html`: Extra stuff before the `</body>` tag.
* `_includes/post-header-home.html`: Customize the post header on the home page.

### Customize the Theme

To change my color theme, edit `_data/theme.yml`.

### Add Your Own CSS

You can customize CSS by editing these files:

* `_sass/custom-bootstrap-variables.scss`: Change Bootstrap variables ([documentation](http://getbootstrap.com/customize)).
* `_sass/custom-scss.scss`: Add your own custom CSS.

## :ribbon: Author :ribbon:

#### Elle Kasai

![](https://avatars0.githubusercontent.com/u/2410692?v=2&s=200)

* http://twitter.com/ellekasai
* http://github.com/ellekasai

#### Special Thanks

* [Shu Uesugi](http://github.com/chibicode) - for the guidance on this project.

### License

[MIT License](http://ellekasai.mit-license.org/)
