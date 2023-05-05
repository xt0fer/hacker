
var listDiv = document.getElementById('list-news');
var ul=document.createElement('ul');
for (var i = 0; i < data.list.length; ++i) {
      var li=document.createElement('li');
      li.innerHTML = data.list[i].title;   // Use innerHTML to set the text
      ul.appendChild(li);                                 
}
listDiv.appendChild(ul);    // Note here
