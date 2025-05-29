package com.jpademo.handle.files.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpademo.handle.files.demo.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
