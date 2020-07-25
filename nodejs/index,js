var Jimp = require("jimp");
var robot = require("robotjs");
var net = require("net");

var lastTime = Date.now();
var client = null;
var fps = 10;

function start() {
console.log(`Running on ${fps}fps (interval of ${(1000/fps).toFixed(2)}ms)`)
    client = net.createConnection({ host: "localhost", port: 4444 }, () => {
        console.log("connected to the minecraft server!");
    });
    client.on("data", (data) => {
        console.log(data.toString());
    });
    client.on("end", () => {
        console.log("disconnected from server");
    });
    function again() {
        var time = Date.now() - lastTime;
        //console.log("taking new screenshot took last image", time + "ms ago");

        var screenSize = robot.getScreenSize();
        var img = robot.screen.capture(0, 0, screenSize.width, screenSize.height);
        print(bufferToJimp(img, screenSize.width, screenSize.height), () => {
            setTimeout(() => {
                again();
                lastTime = Date.now();
            }, 1000/fps);
        });
    }
    again();
}
start();
function bufferToJimp(buffer, width, height) {
    let jimg = new Jimp(width, height);

    let x = 0;
    while (x < width) {
        let y = 0;
        while (y < height) {
            let index = (y * buffer.byteWidth) + (x * buffer.bytesPerPixel);
            let r = buffer.image[index];
            let g = buffer.image[index + 1];
            let b = buffer.image[index + 2];
            let num = (r * 256) + (g * 256 * 256) + (b * 256 * 256 * 256) + 255;
            jimg.setPixelColor(num, x, y);
            y++;
        }
        x++;
    }
    return jimg;
}

function print(image, cb, options = { maxWidth: 189, maxHeight: 189 }) {
    var sizeObj = resize(image.bitmap.width, image.bitmap.height, options.maxWidth, options.maxHeight);

    var width = sizeObj.width;
    var height = sizeObj.height;

    image.resize(width, height);

    //console.log(`resized image to:`, { width, height });

    var resultStr = "";

    let x = 0;
    while (x < width) {
        let y = 0;
        while (y < height) {
            //let clr = image.getPixelColor(x, y);
			let c1 = Jimp.intToRGBA(image.getPixelColor(x, y));
			let clr = `${c1.r}-${c1.g}-${c1.b}`
            //let pixel = Jimp.intToRGBA(image.getPixelColor(x, y));
            //var clr = `#${toHex(pixel.r, pixel.g, pixel.b)}`;
            if (x == width - 1) {
                resultStr += `${x},${y},${clr}|`;
                if (y == height - 1) {//done
                    if(client == null) {
                        console.log("client was null ofo");
                        cb();
                        return;
                    }
                    client.write(resultStr+"\n", (e) => {if(e)console.log("oop",e)});
                    cb();
                    return;
                }
            } else {
                resultStr += `${x},${y},${clr}|`;
            }
            y++;
        }
        x++;
    }

}

function resize(width, height, maxWidth, maxHeight) {
    var ratioX = maxWidth / width;
    var ratioY = maxHeight / height;
    var ratio = Math.min(ratioX, ratioY);

    var newWidth = width * ratio;
    var newHeight = height * ratio;

    return { width: Math.round(newWidth), height: Math.round(newHeight) };
}

function toHex(red, green, blue) {

    if (typeof red === "string") {
        [red, green, blue, alpha] = red.match(/(0?\.?\d{1,3})%?\b/g).map(Number);
    }

    if (typeof red !== "number" ||
        typeof green !== "number" ||
        typeof blue !== "number" ||
        red > 255 ||
        green > 255 ||
        blue > 255
    ) {
        throw new TypeError("Expected three numbers below 256");
    }

    return ((blue | green << 8 | red << 16) | 1 << 24).toString(16).slice(1);
};
