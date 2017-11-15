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
        <h2>Machine Fail Records</h2>
        F Id: <input type="text" id="txtId" placeholder="F Id"/>
        F Line: <input type="text" id="txtLine" placeholder="F Line"/>
        F Machine: <input type="text" id="txtMachine" placeholder="F Machine"/>
        Down Time: <input type="text" id="txtDownTime" placeholder="Down Time"/>
        Restart Time: <input type="text" id="txtRestartTime" placeholder="Restart Time"/>
        F Code: <input type="text" id="txtCode" placeholder="F Code"/>
        F Sub Code: <input type="text" id="txtSubCode" placeholder="F Sub Code"/>
        F Detail: <input type="text" id="txtDetail" placeholder="F Detail"/>
        F Phen: <input type="text" id="txtPhen" placeholder="F Phen"/>
        F Repair Detail: <input type="text" id="txtRepairDetail" placeholder="F Repair Detail"/>
        Worker: <input type="text" id="txtWorker" placeholder="Worker"/>
        Work Start: <input type="text" id="txtWorkStart" placeholder="Work Start"/>
        Work End: <input type="text" id="txtWorkEnd" placeholder="Work End"/>
        Work Duration: <input type="text" id="txtWorkDuration" placeholder="Work Duration"/>
        <br/>
        <button id="btnAdd">Add (+)</button>
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
