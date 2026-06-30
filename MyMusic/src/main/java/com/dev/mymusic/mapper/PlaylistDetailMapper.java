package com.dev.mymusic.mapper;

import com.dev.mymusic.dto.request.PlaylistDetailCreateRequest;
import com.dev.mymusic.dto.response.PlaylistDetailResponse;
import com.dev.mymusic.entity.PlaylistDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistDetailMapper {

    //ignore: là bỏ qua mapping
    @Mapping(target = "id", ignore = true)
    PlaylistDetail toPlaylistDetail(PlaylistDetailCreateRequest playlistDetailCreateRequest);

    //target: là mapping
    //Chuyển các trường dữ liệu cần thiết mà bị khác để map với nhau
    @Mapping(source = "playlist.title", target =  "playlistTitle")
    @Mapping(source = "song.title", target =  "songTitle")
    @Mapping(source = "song.singer", target =  "singer")
    PlaylistDetailResponse playlistDetailResponse(PlaylistDetail playlistDetail);
    
    /*
    Quy tắc mapping:

    B1: JPA CRUD đọc từ entity nhưng mà FE gửi DTORequest cho BE thông qua @RequestBody từ 1 API nào đó,
    sau đó service thực hiện việc gọi đến JPD để thực thi hành động mà DTORequest gửi đến
    thì cần phải MAPPING từ DTORequest thành ENTITY

    B2: Rồi sau đó từ ENTITY MAPPING lại 1 lần nữa thì mới thành DTOResponse

    Request
    - những trường thông tin có tên biến khác nhau thì cần khai báo mapping
    - những trường nào không cần mapping thì ignore đi
     */
}
