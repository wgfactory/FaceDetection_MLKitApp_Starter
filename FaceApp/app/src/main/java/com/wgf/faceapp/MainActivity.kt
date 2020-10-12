package com.wgf.faceapp

import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import com.wgf.faceapp.Helper.FaceGraphicOverlay
import com.wgf.faceapp.Helper.RectOverlay
import com.wonderkiln.camerakit.*
import com.wonderkiln.camerakit.CameraKit.Constants.FACING_BACK
import com.wonderkiln.camerakit.CameraKit.Constants.FACING_FRONT
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name
    val DRAW_FACE_BOX = 1
    val DRAW_FACE_CONTOUR = 2

    lateinit var waitingDialog: AlertDialog

    var camFacing = FACING_BACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO - onCreate() 함수 로그 출력!
        Log.d(TAG, ">> onCreate()")

        // TODO - "잠시만 기다려 주세용!" 팝업창 코드 넣기


        // TODO - 카메라 스위치 버튼 클릭했을 때 코드 넣기


        // TODO - 얼굴 감지! 버튼 클릭했을 때 코드 넣기


        // 카메라 뷰에 대한 코드
        camera_view.addCameraKitListener(object:CameraKitEventListener{
            override fun onVideo(p0: CameraKitVideo?) {
                Log.d(TAG, ">> onVideo")
            }

            override fun onEvent(p0: CameraKitEvent?) {
                //TODO - onEvent() 함수 로그 출력
            }

            override fun onImage(p0: CameraKitImage?) {
                //TODO - onImage() 함수 로그 출력

                waitingDialog.show()
                var bitmap = p0!!.bitmap
                bitmap = Bitmap.createScaledBitmap(bitmap, camera_view.width, camera_view.height, false)

                camera_view.stop()

                runFaceDetector(bitmap)
            }

            override fun onError(p0: CameraKitError?) {
                //TODO - onError() 함수 로그 출력

            }
        })
    }

    private fun runFaceDetector(bitmap: Bitmap?) {
        //TODO - runFaceDetector() 함수 로그 출력

        var image = FirebaseVisionImage.fromBitmap(bitmap!!)

        // TODO - FireBase에서 얼굴 검출을 하기 위한 설정들!!
        var options = // TODO - 코드 넣기

        var detector = // TODO - 코드 넣기

        // 얼굴 검출이 되면 호출될 함수 설정하는 코드!
        detector.detectInImage(image)
            .addOnSuccessListener { result -> processFaceResult(result, DRAW_FACE_CONTOUR) }
            .addOnFailureListener {e -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()}

    }

    // 얼굴 검출이 완료되고 수행되는 결과 함수
    private fun processFaceResult(faceResult: List<FirebaseVisionFace>, drawType: Int) {
        //TODO - processFaceResult() 함수 로그 출력

        var count = 0

        // 사진에서 사람의 얼굴을 못찾았을 때 표시해주기!
        if(faceResult.size == 0) {
            // TODO - showToast() 함수 호출 및 원하는 메세지로 수정하기 코드 넣기

            // TODO - 기다리세요 팝업창 닫기 코드 넣기
            return
        }

        // 사진에서 몇명의 사람을 찾았는지 로그 출력하는 코드
        Log.d(TAG, ">> faceResult.size = $faceResult.size")

        // 사진에서 찾은 사람 그래픽 표시해주는 코드
        for(i in 0 until faceResult.size) {
            var face = faceResult.get(i)

            // 얼굴 찾으면 사각형 박스 그려주기
            if(drawType == DRAW_FACE_BOX) {
                val bounds = face.boundingBox
                val rectOverlay = // TODO - 코드 넣기
                // TODO - 코드 넣기

            }

            // 얼굴 찾으면 사각형 박스 + 얼굴 윤곽석 + 랜드마크 그려주기
            if(drawType == DRAW_FACE_CONTOUR) {
                var faceGraphic = // TODO - 코드 넣기
                grapic_overlay.add(faceGraphic)

            }
            count++
        }

        // Dialog 사라지게 하는 코드
        waitingDialog.dismiss()
        showToast(String.format("인공지능이 감지한 얼굴은 %d 명 입니다!", count), Toast.LENGTH_LONG)
    }

    // 토스트 메세지 표시 하는 함수
    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        // TODO - Toast 메세지 만드는 코드 넣기
    }

    override fun onResume() {
        super.onResume()

        //TODO - onResume() 함수 로그 출력

        // TODO - 카메라 start 하는 넣기
    }

    override fun onPause() {
        super.onPause()

        //TODO onPause() 함수 로그 출력

        // TODO - 카메라 stop 하는 넣기
    }

    override fun onDestroy() {
        super.onDestroy()

        //TODO - onDestroy()함수 로그 출력
    }
}