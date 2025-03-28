"use client";
import React, { useState } from 'react';
import axios from 'axios';

function ImageUpload() {
    const [selectedFile, setSelectedFile] = useState(null);
    const [previewUrl, setPreviewUrl] = useState(null);
    const [uploadedImageUrl, setUploadedImageUrl] = useState(null);
    const [error, setError] = useState(null);

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        
        if (file) {
            // 파일 크기 검증 (5MB)
            if (file.size > 5 * 1024 * 1024) {
                setError('파일 크기는 5MB를 초과할 수 없습니다.');
                return;
            }

            // 미리보기 생성
            const reader = new FileReader();
            reader.onloadend = () => {
                setPreviewUrl(reader.result);
            };
            reader.readAsDataURL(file);

            setSelectedFile(file);
            setError(null);
        }
    };

    const handleUpload = async () => {
        if (!selectedFile) {
            setError('파일을 선택해주세요.');
            return;
        }

        const formData = new FormData();
        formData.append('file', selectedFile);

        try {
            const response = await axios.post('http://localhost:8080/api/images/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            setUploadedImageUrl(response.data);
            setError(null);
        } catch (err) {
            setError(err.response?.data || '업로드 중 오류가 발생했습니다.');
        }
    };

    return (
        <div>
            <input 
                type="file" 
                accept="image/jpeg,image/png,image/gif,image/bmp,image/webp"
                onChange={handleFileChange} 
            />
            <button onClick={handleUpload} disabled={!selectedFile}>
                업로드
            </button>

            {error && <p style={{ color: 'red' }}>{error}</p>}

            {previewUrl && (
                <div>
                    <h3>미리보기</h3>
                    <img 
                        src={previewUrl} 
                        alt="미리보기" 
                        style={{ maxWidth: '300px', maxHeight: '300px' }} 
                    />
                </div>
            )}

            {uploadedImageUrl && (
                <div>
                    <h3>업로드된 이미지</h3>
                    <img 
                        src={`http://localhost:8080${uploadedImageUrl}`} 
                        alt="업로드된 이미지" 
                        style={{ maxWidth: '300px', maxHeight: '300px' }} 
                    />
                    <p>이미지 URL: localhost:8080{uploadedImageUrl}</p>
                </div>
            )}
        </div>
    );
}

export default ImageUpload;