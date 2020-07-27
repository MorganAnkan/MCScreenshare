var Jimp = require("jimp");
var robot = require("robotjs");
var net = require("net");

//var lastTime = Date.now();
var client = null;
var fps = 10;
var screenSize = robot.getScreenSize();
var settings = { maxWidth: 189, maxHeight: 189 };
var mcSize = resize(screenSize.width, screenSize.height, settings.maxWidth, settings.maxHeight);

function start() {
    console.log(`Running on ${fps}fps (interval of ${(1000 / fps).toFixed(2)}ms)`);
    console.log(`screen size: (w)${screenSize.width}x(h)${screenSize.height}, minecraft size: (w)${mcSize.width}x(h)${mcSize.height}`);
    client = net.createConnection({ host: "localhost", port: 4444 }, () => {
        console.log("connected to the minecraft server!");
    });
    client.on("data", (data) => {
        if(JSON.parse(data).type == "click") {
        var datastr = data.toString();
        console.log("recieved: " + datastr);
        var datasplit = datastr.split("\n").filter((a) => { return a !== "" });
        datasplit.forEach((data) => {
            var data = JSON.parse(data);
            clickScreen(data.x, data.y);
        });
        } else if(JSON.parse(data).type == "type") {
            robot.typeString(Buffer.from(JSON.parse(data).text, 'base64').toString('utf-8'));
        }
    });
    client.on("end", () => {
        console.log("disconnected from server");
    });
    function again() {
        //var time = Date.now() - lastTime;
        //console.log("taking new screenshot took last image", time + "ms ago");

        var img = robot.screen.capture(0, 0, screenSize.width, screenSize.height);
        print(bufferToJimp(img, screenSize.width, screenSize.height), () => {
            setTimeout(() => {
                again();
                //lastTime = Date.now();
            }, 1000 / fps);
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

function print(image, cb) {
    var width = mcSize.width;
    var height = mcSize.height;

    image.resize(width, height);

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
                    if (client == null) {
                        console.log("client was null ofo");
                        cb();
                        return;
                    }
                    client.write(resultStr + "\n", (e) => { if (e) console.log("oop", e) });
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

function clickScreen(mcX = 0, mcY = 0) {
    var oldPos = robot.getMousePos();

    var oldX = oldPos.x;
    var oldY = oldPos.x;

    var ratioX = screenSize.width / mcSize.width;
    var ratioY = screenSize.height / mcSize.height;

    var newX = Math.round(Math.max((ratioX * mcX) - ratioX / 2, 0));
    var newY = Math.round(Math.max((ratioY * mcY) - ratioY / 2, 0));

    console.log(`Clicking at x: ${newX}, y: ${newY}`);
    robot.moveMouse(newX, newY);
    robot.mouseClick();
    robot.moveMouse(oldX, oldY);
}

function resize(width, height, maxWidth, maxHeight) {
    var ratioX = maxWidth / width;
    var ratioY = maxHeight / height;
    var ratio = Math.min(ratioX, ratioY);

    var newWidth = width * ratio;
    var newHeight = height * ratio;

    return { width: Math.round(newWidth), height: Math.round(newHeight) };
}

/*function toHex(red, green, blue) {

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
};*/
