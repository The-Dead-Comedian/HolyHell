var minetip = document.getElementById("minetip-tooltip");

$(".minetext").mouseover(function(event) {
  minetip.innerHTML = event.target.dataset.mctitle;
  minetip.style.display = "block";
});

$(".minetext").mouseout(function() {
  minetip.style.display = "none";
});

$(".minetext").mousemove(function (event) {
  pos (minetip, 5, -30, event);
});
  var pos = function (o, x, y, event) {
    var posX = 0, posY = 0;
    var e = event || window.event;
    if (e.pageX || e.pageY) {
        posX = e.pageX;
        posY = e.pageY;
    } else if (e.clientX || e.clientY) {
        posX = event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
        posY = event.clientY + document.documentElement.scrollTop + document.body.scrollTop;
    }
    o.style.position = "absolute";
    o.style.top = (posY + y) + "px";
    o.style.left = (posX + x) + "px";
}

minetip.style.display = "none";

//<div id="minetip-tooltip" style="display: none;"> <span class="minetip-title" id="minetip-text">Minecraft Tip</span> </div>
//<span data-mctitle="Copper++ Addon"></span>
