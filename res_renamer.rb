#!/usr/bin/env ruby
puts "Remove files from drawable to  drawable-xxhdpi, drawable-xhdpi, drawable-hdpi, drawable-mdpi"

res_path = "app/src/main/res/"
folder = "drawable"
i = 0

drawable_path = File.absolute_path(res_path + "drawable/*")

Dir.glob(drawable_path).each do |f|
    file_ext = File.extname(f)
    file_name = File.basename(f, file_ext)
    if file_ext != ".jpg" && file_ext != ".png"
        next
    end

    new_file_name = nil
    real_path = nil

    dot9_file = file_name.end_with?('.9')
    if dot9_file
        file_name = file_name[0..-3]
    end
    
    if file_name.end_with?('_h')
        new_file_name = file_name[0..-3]
        real_path = File.absolute_path(res_path + "drawable-hdpi")
    elsif file_name.end_with?('_m')
        new_file_name = file_name[0..-3]
        real_path = File.absolute_path(res_path + "drawable-mdpi")
    elsif file_name.end_with?('_xh')
        new_file_name = file_name[0..-4]
        real_path = File.absolute_path(res_path + 'drawable-xhdpi')
    elsif file_name.end_with?('_xxh')
        new_file_name = file_name[0..-5]
		real_path = File.absolute_path(res_path + "drawable-xxhdpi")
    end
    if new_file_name
        new_file_name = new_file_name + '.9' if dot9_file
        i += 1
        file_final_name = real_path + '/' + new_file_name + file_ext
        puts "Renaming #{file_name}#{file_ext} => #{file_final_name}"
        File.rename(f, file_final_name)
    end

end

puts "Renaming #{i} files complete."
