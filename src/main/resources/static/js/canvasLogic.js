document.addEventListener('DOMContentLoaded', function () {

    let isDrawing = true; // 기본적으로 선 그리기 활성화
    let isAddingImage = false; // 이미지 삽입 비활성화
    let isSave = 0; //그림의 저장 여부 판단

    //=== 캔버스 관련 설정 ===

    //꽃다발 패키지 사진을 보여주는 canvas 설정
    const imageCanvas = document.getElementById('imageCanvas');
    const imageCtx = imageCanvas.getContext('2d');

    //꽃다발을 꾸미는 캔버스
    const canvas = document.querySelector("#canvas");
    const ctx = canvas.getContext("2d");

    // 백그라운드 이미지 URL 설정 - html 파일에서 값 받아옴
    const img = new Image();
    img.src = myValue;
    console.log(myValue);

    img.onload = () => {
        const canvasWidth = imageCanvas.width;
        const canvasHeight = imageCanvas.height;

        const imgWidth = img.width;
        const imgHeight = img.height;

        const scale = Math.min(canvasWidth / imgWidth, canvasHeight / imgHeight);
        const x = (canvasWidth - imgWidth * scale) / 2;
        const y = (canvasHeight - imgHeight * scale) / 2;

        imageCtx.drawImage(img, x, y, imgWidth * scale, imgHeight * scale);
    };


    function resizeCanvas() {
        const container = document.querySelector("#canvasContainer");
        canvas.width = container.clientWidth;
        canvas.height = container.clientHeight;
        imageCanvas.width = container.clientWidth;
        imageCanvas.height = container.clientHeight;
    }

    window.addEventListener('resize', resizeCanvas);
    resizeCanvas();

    //=== 디자인한 꽃 관련 설정 ===

    //디자인한 꽃 이미지 크기 조절
    let imgSize = 200; // 초기 이미지 사이즈

    const slider = document.getElementById('sizeSlider');
    const imgSliderValue = document.getElementById('imgSliderValue');

    slider.addEventListener('input', function () {
        imgSize = this.value;
        imgSliderValue.textContent = imgSize;
    });


    // 로컬스토리지에서 저장된 이미지를 호출
    const storageKeys = ["savedImage1", "savedImage2", "savedImage3"];

    for (let i = 0; i < storageKeys.length; i++) {
        const imageData = localStorage.getItem(storageKeys[i]);
        if (imageData) {
            displayThumbnail(imageData); // 슬롯 인덱스를 전달
        } else {
            const emptyCanvasDataURL = getEmptyImageDataURL();
            displayThumbnail(emptyCanvasDataURL); // 슬롯 인덱스를 전달
        }

    }


    // 로컬스토리지에서 저장된 이미지를 썸네일 형식으로 표시
    function displayThumbnail(imageData) {
        const thumbnailContainer = document.getElementById('thumbnails');
        const imgElement = document.createElement('img');
        imgElement.src = imageData;

        // 이미지 클릭 시 동작할 기능을 연결
        imgElement.addEventListener('click', function () {
            updateHighlight(imgElement); // 클릭된 썸네일에 하이라이트 추가
            performActionBasedOnImage(imageData);

        });

        thumbnailContainer.appendChild(imgElement);
    }
    //만약 슬롯이 비어있을 경우, 빈 화면을 출력
    function getEmptyImageDataURL() {
        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');
        canvas.width = 150; // 썸네일의 너비
        canvas.height = 150; // 썸네일의 높이
        ctx.fillStyle = '#ffffff00'; // 배경색 (흰색)
        ctx.fillRect(0, 0, canvas.width, canvas.height);
        return canvas.toDataURL('image/png');
    }

    // 이미지 데이터에 따라 다른 기능 수행

    function performActionBasedOnImage(imageData) {

        const savedImage1 = localStorage.getItem('savedImage1');
        const savedImage2 = localStorage.getItem('savedImage2');
        const savedImage3 = localStorage.getItem('savedImage3');
        console.log(page);
        if (imageData === savedImage1) {
            console.log("1번 이미지 선택");
            flowerImg.src = savedImage1;
            switchDrawMode(1);

        } else if (imageData === savedImage2) {
            console.log("2번 이미지 선택");
            flowerImg.src = savedImage2;
            switchDrawMode(2);

        } else if (imageData === savedImage3) {
            console.log("3번 이미지 선택");
            flowerImg.src = savedImage3;
            switchDrawMode(3);

        } else {
            switchDrawMode(0);
        }
    }

    // 섬네일 하이라이트 지정
    const thumbnails = document.querySelectorAll('#thumbnails img');

    function updateHighlight(selectedThumbnail) {

        // 모든 썸네일에서 하이라이트 제거
        thumbnails.forEach(thumbnail => {
            thumbnail.classList.remove('thumbnail-highlight');
        });

        // 선택된 썸네일에 하이라이트 추가
        selectedThumbnail.classList.add('thumbnail-highlight');
    }


    // === 그림 관련 설정 ===
    let painting = false;
    let flowerImg = new Image();
    flowerImg.src = localStorage.getItem('savedImage1');
    let page = 0;

    ctx.lineWidth = 3;  //펜선 굵기
    //펜선 굵기 슬라이드바 로직
    const lineWidthSlider = document.getElementById('lineWidthSlider');
    lineWidthSlider.addEventListener('input', (event) => {
        ctx.lineWidth = event.target.value;
        sliderValue.textContent = event.target.value;
    });

    function stopPainting() {
        painting = false;
        isSave = 0;
    }

    function startPainting() {
        painting = true;
    }


    function onMouseMove(event) {
        if (!isDrawing) return; // 선 그리기가 활성화되어 있지 않으면 아무것도 하지 않음
        const x = event.offsetX;
        const y = event.offsetY;

        if (!painting) {
            ctx.beginPath();
            ctx.moveTo(x, y);
        } else {
            ctx.lineTo(x, y);
            ctx.stroke();
        }
    }

    canvas.onclick = function (event) {
        if (!isAddingImage) return; // 이미지 삽입이 활성화되어 있지 않으면 아무것도 하지 않음

        const x = event.offsetX;
        const y = event.offsetY;

        ctx.drawImage(flowerImg, x - imgSize / 2, y - imgSize / 2, imgSize, imgSize);
    };


    function switchDrawMode(pageId) {
        if ((isDrawing == false && page == pageId) || pageId == 0) {
            page = 0;
            isDrawing = true;
            isAddingImage = false;
            console.log("하이라이트 꺼짐 확인");

            thumbnails.forEach(thumbnail => {
                thumbnail.classList.remove('thumbnail-highlight');
            });
        }
        else {
            page = pageId;
            isDrawing = false;
            isAddingImage = true;
        }
    }

    canvas.addEventListener("mousemove", onMouseMove);
    canvas.addEventListener("mousedown", startPainting);
    canvas.addEventListener("mousedown", saveState);
    canvas.addEventListener("mouseup", stopPainting);
    canvas.addEventListener("mouseleave", stopPainting);

    const buttons = [
        "red", "orange", "yellow", "green", "blue",
        "navy", "purple", "black", "white", "clear",
        "fill", "undo", "redo"
    ];
    let lineColor = "black";

    buttons.forEach((color) => {
        const button = document.querySelector(`.${color}`);
        button.style.background = (color === "clear" || color === "fill") ? "rgba(100,100,100,0.2)" : color;
        button.onclick = () => {
            ctx.strokeStyle = color;
            lineColor = color;
        };
    });

    document.querySelector(".clear").onclick = () => {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
    };

    document.querySelector(".fill").onclick = () => {
        ctx.fillStyle = lineColor;
        ctx.fillRect(0, 0, canvas.width, canvas.height);
    };

    document.querySelector(".undo").onclick = () => {
        console.log(undoStack.length);
        restoreState(undoStack, redoStack);
    };

    document.querySelector(".redo").onclick = () => {
        restoreState(redoStack, undoStack);
    };

    document.querySelector(".drawBtn").onclick = () => {

        isDrawing = true;
        isAddingImage = false;

        thumbnails.forEach(thumbnail => {
            thumbnail.classList.remove('thumbnail-highlight');
        });

    };

    // 실행 취소/복구 기능
    let undoStack = [];
    let redoStack = [];

    function saveState() {

        if (isSave == 0) {
            console.log(undoStack.length);
            console.log("저장")
            isSave = 1;
            undoStack.push(canvas.toDataURL());
        }
        //redoStack = []; // 새로운 작업이 발생하면 redoStack 초기화
    }


    function restoreState(stack, oppositeStack) {
        if (stack.length > 0) {
            oppositeStack.push(canvas.toDataURL());
            const imgData = stack.pop();
            const img = new Image();
            img.src = imgData;
            img.onload = () => {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                ctx.drawImage(img, 0, 0);
                ctx.drawImage(img, 0, 0);
            };
        }
    }

    //=== 파일 관리 ===
    //이미지 다운로드
    function downloadImage() {
        const image = canvas.toDataURL('image/png');
        const link = document.createElement('a');
        link.href = image;
        link.download = 'canvas-image.png';
        link.click();
    }

    const downloadBtn = document.getElementById('downloadBtn');
    downloadBtn.addEventListener('click', downloadImage);

    //이미지 서버에 업로드
    function sendImageToServer() {
        const image = canvas.toDataURL('image/png');
        const blob = dataURItoBlob(image);
        const formData = new FormData();
        formData.append('file', blob, 'canvas-image.png');

        fetch('/spring/drawUpload', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/spring/imageList';
                } else {
                    console.error('File upload failed');
                }
            })
            .catch(error => console.error('Error:', error));
    }

    //저장할 이미지의 타입을 변경하는 코드
    function dataURItoBlob(dataURI) {
        const byteString = atob(dataURI.split(',')[1]);
        const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        const ab = new ArrayBuffer(byteString.length);
        const ia = new Uint8Array(ab);
        for (let i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        return new Blob([ab], {type: mimeString});
    }

    const uploadBtn = document.getElementById('uploadBtn');
    uploadBtn.addEventListener('click', sendImageToServer);

});