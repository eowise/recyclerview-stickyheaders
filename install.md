---
layout: page
published: true
---

# Install

## Using Gradle

Add the following to your build.gradle:

{% highlight groovy %}
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.eowise:recyclerview-stickyheaders:0.5.1@aar'
}
{% endhighlight %}

## Using Maven

Add the following to your pom.xml:

{% highlight xml %}
<dependency>
    <groupId>com.eowise</groupId>
    <artifactId>recyclerview-stickyheaders</artifactId>
    <version>0.5.1</version>
</dependency>
{% endhighlight %}

## Snapshots

Sanpshot are published to SonaType snapshot repository. Add this to your `build.gradle` to use a snapshot version:

{% highlight groovy %}
repositories {
    mavenCentral()
    maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
}

dependencies {
    compile 'com.eowise:recyclerview-stickyheaders:x.y.z-SNAPSHOT@aar'
}
{% endhighlight %}