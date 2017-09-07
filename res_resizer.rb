#!/usr/bin/env ruby
puts "Resize files from xxhdpi to xhdpi , hdpi and mdpi\n imagemagick must be installed!"

res_path = "app/src/main/res/"
folder = "drawable"
i = 0

drawable_path = File.absolute_path(res_path + "drawable/*")

puts drawable_path

Dir.glob(drawable_path).each do |f|
    file_ext = File.extname(f)
    file_name = File.basename(f, file_ext)
    if file_ext != ".jpg" && file_ext != ".png"
        next
    end

    if file_name.end_with?('_xxh')
        i += 1
        hdpi_file_path = "#{res_path}#{folder}/#{file_name[0..-5]}_h#{file_ext}"
        mdpi_file_path = "#{res_path}#{folder}/#{file_name[0..-5]}_m#{file_ext}"
        xhdpi_file_path = "#{res_path}#{folder}/#{file_name[0..-5]}_xh#{file_ext}"
		xxhdpi_file_path = File.absolute_path(f)
        puts "Creating #{hdpi_file_path}"
        system "convert #{xxhdpi_file_path} -resize 50% #{hdpi_file_path}"
        puts "Creating #{mdpi_file_path}"
        system "convert #{xxhdpi_file_path} -resize 33% #{mdpi_file_path}"
		puts "Creating #{xhdpi_file_path}"
        system "convert #{xxhdpi_file_path} -resize 67% #{xhdpi_file_path}"
    end
end

puts "Resizing #{i} files complete."
