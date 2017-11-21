<!DOCTYPE html>
<html>
    <head>
        <title>Machine Fail Records</title>
        <style>
            ul li{
                display: inline;
                padding-right: 5px;
            }
        </style>
    </head>
    <body>
        <h2>Apartment Records</h2>
        Aparment ID:<input type="text" id="txtApartId" placeholder="Apartment ID"/> <br/>
        Aparment Name:<input type="text" id="txtApartName" placeholder="Apartment Name"/> <br/>
        Dong ID:<input type="text" id="txtDongId" placeholder="Dong ID"/> <br/>
        Gu ID:<input type="text" id="txtGuId" placeholder="Gu ID"/> <br/>
        City ID:<input type="text" id="txtCityId" placeholder="City ID"/> <br/>
        <input type="file" name="file" id="file" accept="image/*" multiple>
        <input type="button" value="send" id="send">
        <br/><br/>
        <button id="btnEdit">Edit</button>
        <br/>
        <hr/>
        <br/>
        <table border="1px" width="100%">
            <thead>
                <tr style="text-align: center">
                    <td>Apart ID</td>
                    <td>Apartment Name</td>
                    <td>City ID</td>
                    <td>Gu ID</td>
                    <td>Dong ID</td>
                    <td>Url Image</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody id="Apartment">
                <tr style="text-align: center">
                    <td colspan="16">NO CONTENT</td>
                </tr>
            </tbody>
        </table>
        <div>
            <div>
                <div>Showing <span id="limitPage"></span> of <span id="totalPage"></span> Pages and <span id="totalRecords"></span> Records</div>
            </div>
            <div>
                <div id="PAGINATION">

                </div>
            </div>
        </div>
        <script id="APARTMENT_TEMPLATE" type="text/x-jquery-tmpl">
            <tr style="text-align:center;" data-id="{{= fId}}">
                <td>{{= apart_id}}</td>
                <td>{{= apart_name}}</td>
                <td>{{= city_id}}</td>
                <td>{{= gu_id}}</td>
                <td>{{= dong_id}}</td>
                <td>{{= url_image}}</td>
                <td>
                    <a href="javascript:;" id="btnEdit">
                        <button>Edit</button>
                   	</a>
                   	<a href="javascript:;" id="btnDelete">
                        <button>Del</button>
                   	</a>
                </td>
            </tr>
        </script>
        <script src="jquery-3.2.1.min.js"></script>
        <script src="jquery.tmpl.min.js"></script>
        <script src="jquery.bootpag.min.js"></script>
        <script src="apartment.js"></script>
    </body>
</html>
