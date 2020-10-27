var Jimp = require("jimp");
var robot = require("robotjs");
var net = require("net");

var client = null;
var fps = 1;
var screenSize = robot.getScreenSize();
var settings = { maxWidth: 128, maxHeight: 128 };
var mcSize = resize(screenSize.width, screenSize.height, settings.maxWidth, settings.maxHeight);

function start() {
    console.log(`Running on ${fps}fps (interval of ${(1000 / fps).toFixed(2)}ms)`);
    console.log(`screen size: (w)${screenSize.width}x(h)${screenSize.height}, minecraft size: (w)${mcSize.width}x(h)${mcSize.height}`);
    client = net.createConnection({ host: "localhost", port: 4444 }, () => {
        console.log("connected to the minecraft server!");
    });
    client.on("data", (data) => {
		var datastr = data.toString();
		console.log("recieved: " + datastr);
    });
    client.on("end", () => {
        console.log("disconnected from server");
    });
    function again() {
        var img = robot.screen.capture(0, 0, screenSize.width, screenSize.height);
        print(bufferToJimp(img, screenSize.width, screenSize.height), () => {
            setTimeout(() => {
                again();
            }, 1000 / fps);
        });
    }
    again();
}

start();
function bufferToJimp(buffer, width, height) {
    let jimg = new Jimp(width, height);

    for(let x = 0; x < width; x++) {
        for(let y = 0; y < height; y++) {
            let index = (y * buffer.byteWidth) + (x * buffer.bytesPerPixel);
            let r = buffer.image[index];
            let g = buffer.image[index + 1];
            let b = buffer.image[index + 2];
            let num = (r * 256) + (g * 256 * 256) + (b * 256 * 256 * 256) + 255;
            jimg.setPixelColor(num, x, y);
        }
    }
    return jimg;
}

function print(image, cb) {
    let width = mcSize.width;
    let height = mcSize.height;

    image.resize(width, height);

    let buffer = Buffer.alloc(16 + 8 + width * height * 3);
    let offset = 0;

    for (let i = 0; i < 2; i++) {
        buffer.writeInt32BE(1145455686) //equivalent of [68, 70, 68, 70]
        offset += 4
    }

    buffer.writeInt32BE(width, offset);
    offset += 4;
    buffer.writeInt32BE(height, offset);

    for (let y = 0; y < height; y++) {
        for (let x = 0; x < width; x++) {
            let color = Jimp.intToRGBA(image.getPixelColor(x, y));
            buffer.writeInt8(color.r - 128, offset++);
            buffer.writeInt8(color.g - 128, offset++);
            buffer.writeInt8(color.b - 128, offset++);
        }
    }

    for (let i = 0; i < 2; i++) {
        buffer.writeInt32BE(1145455686) //equivalent of [68, 70, 68, 70]
        offset += 4
    }

    if (client == null) {
        console.log("client was null ofo");
        return cb();
    }
    client.write(buffer, (e) => {
        if (e) console.log("oops write error:", e);
        cb();
    });
}

function resize(width, height, maxWidth, maxHeight) {
    var ratioX = maxWidth / width;
    var ratioY = maxHeight / height;
    var ratio = Math.min(ratioX, ratioY);

    var newWidth = width * ratio;
    var newHeight = height * ratio;

    return { width: Math.round(newWidth), height: Math.round(newHeight) };
}

/*
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
};*/